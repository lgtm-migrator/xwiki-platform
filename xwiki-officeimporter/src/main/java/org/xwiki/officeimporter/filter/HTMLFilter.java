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
package org.xwiki.officeimporter.filter;

import org.w3c.dom.Document;

/**
 * Defines an interface for filters that will filter xhtml documents as necessary.
 * 
 * @version $Id$
 * @since 1.8M1
 */
public interface HTMLFilter
{
    /**
     * White space characters.<br/>
     * <p>
     * \u0020 - Ascii space.<br/>
     * \u0009 - Ascii tab.<br/>
     * \u000C - Ascii form feed. <br/>
     * \u200B - Zero width space.<br/>
     * \u000A - New line.<br/>
     * \u000D - Carriage return.<br/>
     * </p>
     */
    String WHITE_SPACE_CHARS = "\u0020\u0009\u000C\u200B" + "\n\r";

    /**
     * Do some clean action to the html code stored in {@link Document}.
     * 
     * @param document The {@link Document} with html code
     */
    void filter(Document document);
}
