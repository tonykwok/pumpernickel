/**
 * This software is released as part of the Pumpernickel project.
 * 
 * All com.pump resources in the Pumpernickel project are distributed under the
 * MIT License:
 * https://raw.githubusercontent.com/mickleness/pumpernickel/master/License.txt
 * 
 * More information about the Pumpernickel project is available here:
 * https://mickleness.github.io/pumpernickel/
 */
package com.pump.jar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class PumpLicense {


	/** Creates the html-encoded modified BSD license for pump code
	 * in this project.
	 * @throws IOException if an IO problem occurs.
	 */
	public static File createLicenseFile() throws IOException {
		int year = ((new GregorianCalendar()).get(Calendar.YEAR));
		File temp = File.createTempFile("license", ".html");
		temp.deleteOnExit();
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(temp);
			PrintStream ps = new PrintStream(out);
			ps.println("<html>");
			ps.println("Source code, binaries and/or any other resources in the package labeled \"com.pump\" are copyright (c) "+year+" by Jeremy Wood.  They are available under the Modified BSD license (see below).");
			ps.println("<P>Any resources not in the \"com.pump\" package may be subject to additional license terms and restrictions.");
			ps.println("<P>If you have any questions about this jar, the relevant licenses, the source code, etc., please contact <A HREF=\"mailto:mickleness+java@gmail.com\">mickleness+java@gmail.com</A>.");
			ps.println("<P>This jar is part of the \"javagraphics\" project, discussed <A HREF=\"https://javagraphics.java.net/\">here</A>.");
			ps.println("<h3>Modified BSD License</H3>");
			ps.println("<P>Copyright (c) "+year+", Jeremy Wood.");
			ps.println("<BR>All rights reserved.");
			ps.println("<P>Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:");
			ps.println();
			ps.println("<BR> * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.");
			ps.println("<BR> * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.");
			ps.println("<BR> * The name of the contributors may not be used to endorse or promote products derived from this software without specific prior written permission.");
			ps.println("<P>THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS \"AS IS\" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.");
			ps.println("</html>");
			ps.flush();
			return temp;
		} finally {
			if(out!=null) {
				try {
					out.close();
				} catch(Throwable t) {}
			}
		}
	}
}