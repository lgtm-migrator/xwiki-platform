/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.xwiki.test.webstandards.framework;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.lang3.StringUtils;
import org.xwiki.model.reference.DocumentReference;
import org.xwiki.test.integration.junit.LogCaptureConfiguration;
import org.xwiki.test.integration.junit.LogCaptureValidator;
import org.xwiki.validator.ValidationError;
import org.xwiki.validator.Validator;

/**
 * Verifies that all pages in the default wiki are valid HTML documents.
 * 
 * @version $Id$
 */
public class DefaultValidationTest extends AbstractValidationTest
{
    protected Validator validator;

    /**
     * We save the stdout stream since we replace it with our own in order to verify that XWiki doesn't generated any
     * error while validating documents and we fail the build if it does.
     */
    protected PrintStream stdout;

    /**
     * The new stdout stream we're using to replace the default console output.
     */
    protected ByteArrayOutputStream out;

    /**
     * We save the stderr stream since we replace it with our own in order to verify that XWiki doesn't generated any
     * error while validating documents and we fail the build if it does.
     */
    protected PrintStream stderr;

    /**
     * The new stderr stream we're using to replace the default console output.
     */
    protected ByteArrayOutputStream err;

    private LogCaptureValidator logCaptureValidator;
    private LogCaptureConfiguration logCaptureConfiguration;

    public DefaultValidationTest(Target target, HttpClient client, Validator validator, String credentials)
        throws Exception
    {
        super("testDocumentValidity", target, client, credentials);

        this.logCaptureValidator = new LogCaptureValidator();
        this.validator = validator;
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        this.logCaptureConfiguration = new LogCaptureConfiguration();
        this.registerExpectedLogs();

        // TODO Until we find a way to incrementally display the result of tests this stays
        System.out.println(getName());

        // We redirect the stdout and the stderr in order to detect (server-side) error/warning
        // messages like the ones generated by the velocity parser
        this.stdout = System.out;
        this.out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(this.out));
        this.stderr = System.err;
        this.err = new ByteArrayOutputStream();
        System.setErr(new PrintStream(this.err));
    }

    private void registerExpectedLogs()
    {
        Target resetPassword = new DocumentReferenceTarget(new DocumentReference("xwiki", "XWiki", "ResetPassword",
            Locale.ROOT));
        if (this.target.equals(resetPassword)) {
            this.logCaptureConfiguration.registerExpected("[DEPRECATED] The page [XWiki.ResetPassword] "
                + "should not be used anymore in favor of the new 'authenticate/reset' URL.");
        }

        Target resetPasswordComplete = new DocumentReferenceTarget(
            new DocumentReference("xwiki", "XWiki", "ResetPasswordComplete", Locale.ROOT));
        if (this.target.equals(resetPasswordComplete)) {
            this.logCaptureConfiguration.registerExpected("[DEPRECATED] The page [XWiki.ResetPasswordComplete] "
                + "should not be used anymore in favor of the new 'authenticate/reset' URL.");
        }

        Target forgotUsername = new DocumentReferenceTarget(
            new DocumentReference("xwiki", "XWiki", "ForgotUsername", Locale.ROOT));
        if (this.target.equals(forgotUsername)) {
            this.logCaptureConfiguration.registerExpected("[DEPRECATED] The page [XWiki.ForgotUsername] "
                + "should not be used anymore in favor of the new 'authenticate/forgot' URL.");
        }
    }

    @Override
    protected void tearDown() throws Exception
    {
        // Restore original stdout and stderr streams.
        String output = this.out.toString();
        String errput = this.err.toString();

        System.setOut(this.stdout);
        System.out.print(output);
        System.setErr(this.stderr);
        System.err.print(errput);

        // Detect server-side error/warning messages from the stdout
        assertFalse(String.format("Errors found in the stdout output: [%s]", output), hasLogErrors(output));
        this.logCaptureValidator.validate(output, this.logCaptureConfiguration, false);

        // Detect server-side error/warning messages from the stderr
        assertFalse(String.format("Errors found in the stderr output: [%s]", errput), hasLogErrors(errput));
        this.logCaptureValidator.validate(errput, this.logCaptureConfiguration);

        super.tearDown();
    }

    @Override
    public String getName()
    {
        return String.format("Validating %s validity for [%s] executed %s",
            this.validator.getName(), getTargetURL(this.target),
            (credentials == null ? "as guest" : "with credentials " + credentials));
    }

    public void testDocumentValidity() throws Exception
    {
        byte[] responseBody = getResponseBody();

        this.validator.setDocument(new ByteArrayInputStream(responseBody));
        List<ValidationError> errors = this.validator.validate();

        Set<Integer> errorLines = new HashSet<>();

        StringBuffer message = new StringBuffer();
        message.append("Validation errors in " + this.target.getName());
        boolean hasError = false;
        for (ValidationError error : errors) {
            if (error.getType() == ValidationError.Type.WARNING) {
                if (error.getLine() >= 0) {
                    System.out
                        .println(
                            "Warning at " + error.getLine() + ":" + error.getColumn() + " " + error.getMessage());
                } else {
                    System.out.println("Warning " + error.getMessage());
                }
            } else {
                if (error.getLine() >= 0) {
                    message.append("\n" + error.toString() + " at line [" + error.getLine() + "] column ["
                        + error.getColumn() + "]");
                } else {
                    message.append("\n" + error.toString());
                }

                errorLines.add(error.getLine() - 2);
                errorLines.add(error.getLine() - 1);
                errorLines.add(error.getLine());
                errorLines.add(error.getLine() + 1);
                errorLines.add(error.getLine() + 2);

                hasError = true;
            }
        }

        if (hasError) {
            System.err.println("");
            System.err.println("Validated content:");

            message.append("\n\nExtracts of the invalid content:");

            BufferedReader reader = new BufferedReader(new StringReader(new String(responseBody)));
            int index = 1;
            int lastErrorLine = -1;
            for (String line = reader.readLine(); line != null; line = reader.readLine(), ++index) {
                StringBuilder lineMessage = new StringBuilder();
                lineMessage.append(index + "\t" + line);

                System.err.println(lineMessage);

                if (errorLines.contains(index)) {
                    if (lastErrorLine != index - 1) {
                        message.append('\n');
                    }

                    message.append("\n  " + lineMessage);

                    lastErrorLine = index;
                }
            }

            fail(message.toString());
        }
    }

    protected boolean hasLogErrors(String output)
    {
        return StringUtils.containsIgnoreCase(output, "error") || StringUtils.containsIgnoreCase(output, "err");
    }

    protected boolean hasLogWarnings(String output)
    {
        return StringUtils.containsIgnoreCase(output, "warning") || StringUtils.containsIgnoreCase(output, "warn");
    }
}
