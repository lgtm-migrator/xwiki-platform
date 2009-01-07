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
package org.xwiki.officeimporter.internal;

import org.xwiki.bridge.DocumentAccessBridge;
import org.xwiki.component.descriptor.ComponentDescriptor;
import org.xwiki.component.descriptor.DefaultComponentDescriptor;

/**
 * Mock {@link DocumentAccessBridge} implementation used for testing, since we don't want to pull any dependency on the
 * Model/Skin/etc for the office importer module's unit tests.
 * 
 * @version $Id$
 * @since 1.8M1
 */
public class MockDocumentAccessBridge implements DocumentAccessBridge
{
    /**
     * Create and return a descriptor for this component.
     * 
     * @return the descriptor of the component.
     */
    public static ComponentDescriptor getComponentDescriptor()
    {
        DefaultComponentDescriptor componentDescriptor = new DefaultComponentDescriptor();

        componentDescriptor.setRole(DocumentAccessBridge.ROLE);
        componentDescriptor.setRoleHint("default");
        componentDescriptor.setImplementation(MockDocumentAccessBridge.class.getName());

        return componentDescriptor;
    }
    
    /**
     * {@inheritDoc}
     * 
     * @see DocumentAccessBridge#exists(String)
     */
    public boolean exists(String documentName) throws Exception
    {
        throw new RuntimeException("Not implemented");
    }

    /**
     * {@inheritDoc}
     * 
     * @see DocumentAccessBridge#getAttachmentContent(String, String)
     */
    public byte[] getAttachmentContent(String documentName, String attachmentName)
        throws Exception
    {
        throw new RuntimeException("Not implemented");
    }

    /**
     * {@inheritDoc}
     * 
     * @see DocumentAccessBridge#getAttachmentURL(String, String)
     */
    public String getAttachmentURL(String documentName, String attachmentName) throws Exception
    {
        // Just to make sure that the bridge was invoked.
        return "/bridge/" + attachmentName;
    }

    /**
     * {@inheritDoc}
     * 
     * @see DocumentAccessBridge#getCurrentUser()
     */
    public String getCurrentUser()
    {
        throw new RuntimeException("Not implemented");
    }

    /**
     * {@inheritDoc}
     * 
     * @see DocumentAccessBridge#getDefaultEncoding()
     */
    public String getDefaultEncoding()
    {
        throw new RuntimeException("Not implemented");
    }

    /**
     * {@inheritDoc}
     * 
     * @see DocumentAccessBridge#getDocumentContent(String)
     */
    public String getDocumentContent(String documentName) throws Exception
    {
        throw new RuntimeException("Not implemented");
    }

    /**
     * {@inheritDoc}
     * 
     * @see DocumentAccessBridge#getDocumentContent(String, String)
     */
    public String getDocumentContent(String documentName, String language) throws Exception
    {
        throw new RuntimeException("Not implemented");
    }

    /**
     * {@inheritDoc}
     * 
     * @see DocumentAccessBridge#getDocumentContentForDefaultLanguage(String)
     */
    public String getDocumentContentForDefaultLanguage(String documentName) throws Exception
    {
        throw new RuntimeException("Not implemented");
    }

    /**
     * {@inheritDoc}
     * 
     * @see DocumentAccessBridge#getProperty(String, String, int, String)
     */
    public String getProperty(String documentName, String className, int objectNumber,
        String propertyName) throws Exception
    {
        throw new RuntimeException("Not implemented");
    }
    
    /**
     * {@inheritDoc}
     * 
     * @see DocumentAccessBridge#getProperty(String, String, String)
     */
    public String getProperty(String documentName, String className, String propertyName)
        throws Exception
    {
        throw new RuntimeException("Not implemented");
    }

    /**
     * {@inheritDoc}
     * 
     * @see DocumentAccessBridge#getProperty(String, String)
     */
    public String getProperty(String documentName, String propertyName) throws Exception
    {
        throw new RuntimeException("Not implemented");
    }

    /**
     * {@inheritDoc}
     * 
     * @see DocumentAccessBridge#getPropertyType(String, String)
     */
    public String getPropertyType(String className, String propertyName) throws Exception
    {
        throw new RuntimeException("Not implemented");
    }

    /**
     * {@inheritDoc}
     * 
     * @see DocumentAccessBridge#getURL(String, String, String, String)
     */
    public String getURL(String documentName, String action, String queryString, String anchor)
        throws Exception
    {
        throw new RuntimeException("Not implemented");
    }

    /**
     * {@inheritDoc}
     * 
     * @see DocumentAccessBridge#hasProgrammingRights()
     */
    public boolean hasProgrammingRights()
    {
        throw new RuntimeException("Not implemented");
    }

    /**
     * {@inheritDoc}
     * 
     * @see DocumentAccessBridge#isDocumentEditable(String)
     */
    public boolean isDocumentEditable(String documentName)
    {
        throw new RuntimeException("Not implemented");
    }

    /**
     * {@inheritDoc}
     * 
     * @see DocumentAccessBridge#isDocumentViewable(String)
     */
    public boolean isDocumentViewable(String documentName)
    {
        throw new RuntimeException("Not implemented");
    }

    /**
     * {@inheritDoc}
     * 
     * @see DocumentAccessBridge#isPropertyCustomMapped(String, String)
     */
    public boolean isPropertyCustomMapped(String className, String propertyName) throws Exception
    {
        throw new RuntimeException("Not implemented");
    }

    /**
     * {@inheritDoc}
     * 
     * @see DocumentAccessBridge#setAttachmentContent(String, String, byte[])
     */
    public void setAttachmentContent(String documentName, String attachmentName,
        byte[] attachmentData) throws Exception
    {
        throw new RuntimeException("Not implemented");
    }

    /**
     * {@inheritDoc}
     * 
     * @see DocumentAccessBridge#setDocumentContent(String, String, String, boolean)
     */
    public void setDocumentContent(String documentName, String content, String editComment,
        boolean isMinorEdit) throws Exception
    {
        throw new RuntimeException("Not implemented");
    }

    /**
     * {@inheritDoc}
     * 
     * @see DocumentAccessBridge#setDocumentSyntaxId(String, String)
     */
    public void setDocumentSyntaxId(String documentName, String syntaxId) throws Exception
    {
        throw new RuntimeException("Not implemented");
    }
}
