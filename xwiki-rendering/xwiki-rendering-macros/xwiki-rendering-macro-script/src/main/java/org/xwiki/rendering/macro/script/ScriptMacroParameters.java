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
package org.xwiki.rendering.macro.script;

import org.xwiki.rendering.macro.descriptor.annotation.ParameterDescription;

/**
 * Parameters for the {@link AbstractScriptMacro} Macro.
 * 
 * @version $Id$
 * @since 1.7M3
 */
public class ScriptMacroParameters
{
    /**
     * @see #setOutput(boolean)
     */
    private boolean output = true;

    /**
     * @see #setWiki(boolean)
     */
    private boolean wiki = true;

    /**
     * @param output indicate the output result has to be inserted back in the document.
     */
    @ParameterDescription("indicate the output result has to be inserted back in the document")
    public void setOutput(boolean output)
    {
        this.output = output;
    }

    /**
     * @return indicate the output result has to be inserted back in the document.
     */
    public boolean isOutput()
    {
        return this.output;
    }

    /**
     * @param wiki indicate if the result of the script execution has to be parsed by the current wiki parser. If not
     *            it's put in a verbatim block.
     * @since 2.0M1
     */
    @ParameterDescription("indicate if the result of the script execution has to be parsed by the current wiki parser."
        + " If not it's put in a verbatim block")
    public void setWiki(boolean wiki)
    {
        this.wiki = wiki;
    }

    /**
     * @return indicate if the result of the script execution has to be parsed by the current wiki parser.
     * @since 2.0M1
     */
    public boolean isWiki()
    {
        return this.wiki;
    }
}
