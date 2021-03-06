package com.churchgroup.util.csv;


/**
 * Write files in comma separated value format.
 * Copyright (C) 2001-2007 Stephen Ostermiller
 * http://ostermiller.org/contact.pl?regarding=Java+Utilities
 * Copyright (C) 2003 Pierre Dittgen <pierre dot dittgen at pass-tech dot fr>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * See COPYING.TXT for details.
 */

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;

/**
 * Print values as a comma separated list.
 * More information about this class is available from <a target="_top" href=
 * "http://ostermiller.org/utils/CSV.html">ostermiller.org</a>.
 *
 * @author Stephen Ostermiller http://ostermiller.org/contact.pl?regarding=Java+Utilities
 * @author Pierre Dittgen <pierre dot dittgen at pass-tech dot fr>
 * @since ostermillerutils 1.00.00
 */
public class CSVPrinter {

	/**
	 * Default state of auto flush
	 */
	private static final boolean AUTO_FLUSH_DEFAULT = true;

	/**
	 * If auto flushing is enabled.
	 *
	 * @since ostermillerutils 1.02.26
	 */
	protected boolean autoFlush = AUTO_FLUSH_DEFAULT;

	/**
	 * Default state of always quote
	 */
	private static final boolean ALWAYS_QUOTE_DEFAULT = false;

	/**
	 * If auto flushing is enabled.
	 *
	 * @since ostermillerutils 1.02.26
	 */
	protected boolean alwaysQuote = ALWAYS_QUOTE_DEFAULT;

	/**
	 * true iff an error has occurred.
	 *
	 * @since ostermillerutils 1.02.26
	 */
	protected boolean error = false;

	/**
	 * Default delimiter character
	 */
	private static final char DELIMITER_DEFAULT = ',';

	/**
	 * Character written as field delimiter.
	 *
	 * @since ostermillerutils 1.02.18
	 */
	protected char delimiterChar = DELIMITER_DEFAULT;

	/**
	 * Default quoting character
	 */
	private static final char QUOTE_DEFAULT = '"';

	/**
	 * Quoting character.
	 *
	 * @since ostermillerutils 1.02.18
	 */
	protected char quoteChar = QUOTE_DEFAULT;

	/**
	 * The place that the values get written.
	 *
	 * @since ostermillerutils 1.00.00
	 */
	protected Writer out;

	/**
	 * True iff we just began a new line.
	 *
	 * @since ostermillerutils 1.00.00
	 */
	protected boolean newLine = true;

	/**
	 * Default start of comments
	 */
	private static final char COMMENT_START_DEFAULT = '#';

	/**
	 * Character used to start comments. (Default is '#')
	 *
	 * @since ostermillerutils 1.00.00
	 */
	protected char commentStart = COMMENT_START_DEFAULT;

	/**
	 * Line ending default
	 */
	private static final String LINE_ENDING_DEFAULT = "\n";

	/**
	 * Line ending indicating the system line ending should be chosen
	 */
	private static final String LINE_ENDING_SYSTEM = null;

	/**
	 * The line ending, must be one of "\n", "\r", or "\r\n"
	 *
	 * @since ostermillerutils 1.06.01
	 */
	protected String lineEnding = LINE_ENDING_DEFAULT;

	/**
	 * Change this printer so that it uses a new delimiter.
	 *
	 * @param newDelimiter The new delimiter character to use.
	 * @throws BadDelimiterException if the character cannot be used as a delimiter.
	 *
	 * @author Pierre Dittgen <pierre dot dittgen at pass-tech dot fr>
	 * @since ostermillerutils 1.02.18
	 */
	public void changeDelimiter(char newDelimiter) {
		if (delimiterChar == newDelimiter) return; // no need to do anything.
/*		if (newDelimiter == '\n' || newDelimiter == '\r' ||
				newDelimiter == delimiterChar || newDelimiter == quoteChar){
			
		}
*/		delimiterChar = newDelimiter;
	}

	/**
	 * Change this printer so that it uses a new character for quoting.
	 *
	 * @param newQuote The new character to use for quoting.
	 * @throws BadQuoteException if the character cannot be used as a quote.
	 *
	 * @author Pierre Dittgen <pierre dot dittgen at pass-tech dot fr>
	 * @since ostermillerutils 1.02.18
	 */
	public void changeQuote(char newQuote) {
		if (quoteChar == newQuote) return; // no need to do anything.
		/*if (newQuote == '\n' || newQuote == '\r' ||
				newQuote == delimiterChar || newQuote == quoteChar){
			
		}*/
		quoteChar = newQuote;
	}

	/**
	 * Change this printer so that it uses a new line ending.
	 * <p>
	 * A line ending must be one of "\n", "\r", or "\r\n".
	 * <p>
	 * The default line ending is the system line separator as specified by
	 * <code>System.getProperty("line.separator")</code>, or "\n" if the system
	 * line separator is not a legal line ending.
	 *
	 * @param lineEnding The new line ending, or null to use the default line ending.
	 * @throws BadLineEndingException if the line ending is not one of the three legal line endings.
	 *
	 * @since ostermillerutils 1.06.01
	 */
	public void setLineEnding(String lineEnding)  {
		boolean setDefault = lineEnding == null;
		if (setDefault){
			lineEnding = System.getProperty("line.separator");
		}
		if (!"\n".equals(lineEnding) && !"\r".equals(lineEnding) && !"\r\n".equals(lineEnding) && setDefault){
		//	if (setDefault){
				lineEnding = LINE_ENDING_DEFAULT;
		//	} 
		}
		this.lineEnding = lineEnding;
	}

	/**
	 * Create a printer that will print values to the given
	 * stream.	 Character to byte conversion is done using
	 * the default character encoding.	Comments will be
	 * written using the default comment character '#', the delimiter will
	 * be the comma, the line ending will be the default system line ending,
	 * the quote character will be double quotes,
	 * quotes will be used when needed, and auto flushing
	 * will be enabled.
	 *
	 * @param out stream to which to print.
	 *
	 * @since ostermillerutils 1.00.00
	 */
	public CSVPrinter(OutputStream out){
		this(
			new OutputStreamWriter(out)
		);
	}

	/**
	 * Create a printer that will print values to the given
	 * stream.	Comments will be
	 * written using the default comment character '#', the delimiter will
	 * be the comma, the line ending will be the default
	 * system line ending, the quote character will be double quotes,
	 * quotes will be used when needed, and auto flushing
	 * will be enabled.
	 *
	 * @param out stream to which to print.
	 *
	 * @since ostermillerutils 1.00.00
	 */
	public CSVPrinter(Writer out){
		this(
			out,
			COMMENT_START_DEFAULT
		);
	}

	/**
	 * Create a printer that will print values to the given
	 * stream.	 Character to byte conversion is done using
	 * the default character encoding.  The delimiter will
	 * be the comma, the line ending will be the default system
	 * line ending, the quote character will be double quotes,
	 * quotes will be used when needed, and auto flushing
	 * will be enabled.
	 *
	 * @param out stream to which to print.
	 * @param commentStart Character used to start comments.
	 *
	 * @since ostermillerutils 1.00.00
	 */
	public CSVPrinter(OutputStream out, char commentStart){
		this(
			new OutputStreamWriter(out),
			commentStart
		);
	}

	/**
	 * Create a printer that will print values to the given
	 * stream.  The delimiter will
	 * be the comma, the line ending will be the default
	 * system line ending, the quote character will be double quotes,
	 * quotes will be used when needed, and auto flushing
	 * will be enabled.
	 *
	 * @param out stream to which to print.
	 * @param commentStart Character used to start comments.
	 *
	 * @since ostermillerutils 1.00.00
	 */
	public CSVPrinter(Writer out, char commentStart){
		this(
			out,
			commentStart,
			QUOTE_DEFAULT,
			DELIMITER_DEFAULT
		);
	}

	/**
	 * Create a printer that will print values to the given
	 * stream.	The comment character will be the number sign, the delimiter will
	 * be the comma, the line ending will be the default
	 * system line ending, and the quote character will be double quotes.
	 *
	 * @param out stream to which to print.
	 * @param alwaysQuote true if quotes should be used even when not strictly needed.
	 * @param autoFlush should auto flushing be enabled.
	 *
	 * @since ostermillerutils 1.02.26
	 */
	public CSVPrinter(Writer out, boolean alwaysQuote, boolean autoFlush){
		this(
			out,
			COMMENT_START_DEFAULT,
			QUOTE_DEFAULT,
			DELIMITER_DEFAULT,
			alwaysQuote,
			autoFlush
		);
	}

	/**
	 * Create a printer that will print values to the given
	 * stream.	 The line ending will be the default system line
	 * ending, quotes will be used when needed, and auto flushing
	 * will be enabled.
	 *
	 * @param out stream to which to print.
	 * @param commentStart Character used to start comments.
	 * @param delimiter The new delimiter character to use.
	 * @param quote The new character to use for quoting.
	 * @throws BadQuoteException if the character cannot be used as a quote.
	 * @throws BadDelimiterException if the character cannot be used as a delimiter.
	 *
	 * @since ostermillerutils 1.02.26
	 */
	public CSVPrinter(Writer out, char commentStart, char quote, char delimiter) {
		this(
			out,
			commentStart,
			quote,
			delimiter,
			LINE_ENDING_SYSTEM
		);
	}

	/**
	 * Create a printer that will print values to the given
	 * stream.	Quotes will be used when needed, and auto flushing
	 * will be enabled.
	 *
	 * @param out stream to which to print.
	 * @param commentStart Character used to start comments.
	 * @param delimiter The new delimiter character to use.
	 * @param quote The new character to use for quoting.
	 * @param lineEnding The new line ending, or null to use the default line ending.
	 * @throws BadQuoteException if the character cannot be used as a quote.
	 * @throws BadDelimiterException if the character cannot be used as a delimiter.
	 * @throws BadLineEndingException if the line ending is not one of the three legal line endings.
	 *
	 * @since ostermillerutils 1.06.01
	 */
	public CSVPrinter(Writer out, char commentStart, char quote, char delimiter, String lineEnding){
		this(
			out,
			commentStart,
			quote,
			delimiter,
			lineEnding,
			ALWAYS_QUOTE_DEFAULT,
			AUTO_FLUSH_DEFAULT
		);
	}

	/**
	 * Create a printer that will print values to the given
	 * stream.  The line ending will be the default system line ending,
	 *
	 * @param out stream to which to print.
	 * @param commentStart Character used to start comments.
	 * @param delimiter The new delimiter character to use.
	 * @param quote The new character to use for quoting.
	 * @param alwaysQuote true if quotes should be used even when not strictly needed.
	 * @param autoFlush should auto flushing be enabled.
	 * @throws BadQuoteException if the character cannot be used as a quote.
	 * @throws BadDelimiterException if the character cannot be used as a delimiter.
	 *
	 * @since ostermillerutils 1.02.26
	 */
	public CSVPrinter(Writer out, char commentStart, char quote, char delimiter, boolean alwaysQuote, boolean autoFlush)  {
		this (
			out,
			commentStart,
			quote,
			delimiter,
			LINE_ENDING_SYSTEM,
			alwaysQuote,
			autoFlush
		);
	}

	/**
	 * Create a printer that will print values to the given
	 * stream.
	 *
	 * @param out stream to which to print.
	 * @param commentStart Character used to start comments.
	 * @param delimiter The new delimiter character to use.
	 * @param lineEnding The new line ending, or null to use the default line ending.
	 * @param quote The new character to use for quoting.
	 * @param alwaysQuote true if quotes should be used even when not strictly needed.
	 * @param autoFlush should auto flushing be enabled.
	 * @throws BadQuoteException if the character cannot be used as a quote.
	 * @throws BadDelimiterException if the character cannot be used as a delimiter.
	 * @throws BadLineEndingException if the line ending is not one of the three legal line endings.
	 *
	 * @since ostermillerutils 1.06.01
	 */
	public CSVPrinter(Writer out, char commentStart, char quote, char delimiter, String lineEnding, boolean alwaysQuote, boolean autoFlush) {
		this.out = out;
		this.commentStart = commentStart;
		changeQuote(quote);
		changeDelimiter(delimiter);
		setLineEnding(lineEnding);
		setAlwaysQuote(alwaysQuote);
		setAutoFlush(autoFlush);
	}

	/**
	 * Print the string as the last value on the line.	The value
	 * will be quoted if needed.
	 * <p>
	 * This method never throws an I/O exception. The client may inquire as to whether
	 * any errors have occurred by invoking checkError().  If an I/O Exception is
	 * desired, the client should use the corresponding writeln method.
	 *
	 * @param value value to be outputted.
	 *
	 * @since ostermillerutils 1.00.00
	 */
	public void println(String value){
		try {
			writeln(value);
		} catch (IOException iox){
			error = true;
		}
	}

	/**
	 * Print the string as the last value on the line.	The value
	 * will be quoted if needed.
	 *
	 * @param value value to be outputted.
	 * @throws IOException if an error occurs while writing.
	 *
	 * @since ostermillerutils 1.02.26
	 */
	public void writeln(String value) throws IOException {
		try {
			write(value);
			writeln();
		} catch (IOException iox){
			error = true;
			throw iox;
		}
	}

	/**
	 * Output a blank line.
	 * <p>
	 * This method never throws an I/O exception. The client may inquire as to whether
	 * any errors have occurred by invoking checkError().  If an I/O Exception is
	 * desired, the client should use the corresponding writeln method.
	 *
	 * @since ostermillerutils 1.00.00
	 */
	public void println(){
		try {
			writeln();
		} catch (IOException iox){
			error = true;
		}
	}

	/**
	 * Output a blank line.
	 *
	 * @throws IOException if an error occurs while writing.
	 *
	 * @since ostermillerutils 1.02.26
	 */
	public void writeln() throws IOException {
		try {
			out.write(lineEnding);
			if (autoFlush) flush();
			newLine = true;
		} catch (IOException iox){
			error = true;
			throw iox;
		}
	}

	/**
	 * Print a single line of comma separated values.
	 * The values will be quoted if needed.  Quotes and
	 * and other characters that need it will be escaped.
	 * <p>
	 * This method never throws an I/O exception. The client may inquire as to whether
	 * any errors have occurred by invoking checkError().  If an I/O Exception is
	 * desired, the client should use the corresponding writeln method.
	 *
	 * @param values values to be outputted.
	 *
	 * @since ostermillerutils 1.00.00
	 */
	public void println(String[] values){
		try {
			writeln(values);
		} catch (IOException iox){
			error = true;
		}
	}

	/**
	 * Print a single line of comma separated values.
	 * The values will be quoted if needed.  Quotes and
	 * and other characters that need it will be escaped.
	 *
	 * @param values values to be outputted.
	 * @throws IOException if an error occurs while writing.
	 *
	 * @since ostermillerutils 1.02.26
	 */
	public void writeln(String[] values) throws IOException {
		try {
			print(values);
			writeln();
		} catch (IOException iox){
			error = true;
			throw iox;
		}
	}

	/**
	 * Print a single line of comma separated values.
	 * The values will be quoted if needed.  Quotes and
	 * and other characters that need it will be escaped.
	 * <p>
	 * This method never throws an I/O exception. The client may inquire as to whether
	 * any errors have occurred by invoking checkError().  If an I/O Exception is
	 * desired, the client should use the corresponding writeln method.
	 *
	 * @param values values to be outputted.
	 *
	 * @since ostermillerutils 1.00.00
	 */
	public void print(String[] values){
		try {
			write(values);
		} catch (IOException iox){
			error = true;
		}
	}

	/**
	 * Print a single line of comma separated values.
	 * The values will be quoted if needed.  Quotes and
	 * and other characters that need it will be escaped.
	 *
	 * @param values values to be outputted.
	 * @throws IOException if an error occurs while writing.
	 *
	 * @since ostermillerutils 1.02.26
	 */
	public void write(String[] values) throws IOException {
		try {
			for (int i=0; i<values.length; i++){
				write(values[i]);
			}
		} catch (IOException iox){
			error = true;
			throw iox;
		}
	}

	/**
	 * Print several lines of comma separated values.
	 * The values will be quoted if needed.  Quotes and
	 * newLine characters will be escaped.
	 * <p>
	 * This method never throws an I/O exception. The client may inquire as to whether
	 * any errors have occurred by invoking checkError().  If an I/O Exception is
	 * desired, the client should use the corresponding writeln method.
	 *
	 * @param values values to be outputted.
	 *
	 * @since ostermillerutils 1.00.00
	 */
	public void println(String[][] values){
		try {
			writeln(values);
		} catch (IOException iox){
			error = true;
		}
	}

	/**
	 * Print several lines of comma separated values.
	 * The values will be quoted if needed.  Quotes and
	 * newLine characters will be escaped.
	 *
	 * @param values values to be outputted.
	 * @throws IOException if an error occurs while writing.
	 *
	 * @since ostermillerutils 1.02.26
	 */
	public void writeln(String[][] values) throws IOException {
		try {
			for (int i=0; i<values.length; i++){
				writeln(values[i]);
			}
			if (values.length == 0){
				writeln();
			}
		} catch (IOException iox){
			error = true;
			throw iox;
		}
	}

	/**
	 * Put a comment among the comma separated values.
	 * Comments will always begin on a new line and occupy a
	 * least one full line. The character specified to star
	 * comments and a space will be inserted at the beginning of
	 * each new line in the comment.  If the comment is null,
	 * an empty comment is outputted.
	 * <p>
	 * This method never throws an I/O exception. The client may inquire as to whether
	 * any errors have occurred by invoking checkError().  If an I/O Exception is
	 * desired, the client should use the corresponding writelnComment method.
	 *
	 * @param comment the comment to output.
	 *
	 * @since ostermillerutils 1.00.00
	 */
	public void printlnComment(String comment){
		try {
			writelnComment(comment);
		} catch (IOException iox){
			error = true;
		}
	}

	/**
	 * Put a comment among the comma separated values.
	 * Comments will always begin on a new line and occupy a
	 * least one full line. The character specified to star
	 * comments and a space will be inserted at the beginning of
	 * each new line in the comment.  If the comment is null,
	 * an empty comment is outputted.
	 *
	 * @param comment the comment to output.
	 * @throws IOException if an error occurs while writing.
	 *
	 * @since ostermillerutils 1.02.26
	 */
	public void writelnComment(String comment) throws IOException {
		try {
			if (comment==null) comment = "";
			if (!newLine){
				writeln();
			}
			out.write(commentStart);
			out.write(' ');
			for (int i=0; i<comment.length(); i++){
				char c = comment.charAt(i);
				switch (c){
					case '\r': {
						if (i+1 < comment.length() && comment.charAt(i+1) == '\n'){
							i++;
						}
					} //break intentionally excluded.
					case '\n': {
						writeln();
						out.write(commentStart);
						out.write(' ');
					} break;
					default: {
						out.write(c);
					} break;
				}
			}
			writeln();
		} catch (IOException iox){
			error = true;
			throw iox;
		}
	}

	/**
	 * Print the string as the next value on the line.	The value
	 * will be quoted if needed.  If value is null, an empty value is printed.
	 * <p>
	 * This method never throws an I/O exception. The client may inquire as to whether
	 * any errors have occurred by invoking checkError().  If an I/O Exception is
	 * desired, the client should use the corresponding println method.
	 *
	 * @param value value to be outputted.
	 *
	 * @since ostermillerutils 1.00.00
	 */
	public void print(String value){
		try {
			write(value);
		} catch (IOException iox){
			error = true;
		}
	}

	/**
	 * Print the string as the next value on the line.	The value
	 * will be quoted if needed.  If value is null, an empty value is printed.
	 *
	 * @param value value to be outputted.
	 * @throws IOException if an error occurs while writing.
	 *
	 * @since ostermillerutils 1.02.26
	 */
	public void write(String value) throws IOException {
		try {
			if (value == null) value = "";
			boolean quote = false;
			if (alwaysQuote){
				quote = true;
			} else if (value.length() > 0){
				char c = value.charAt(0);
				if (newLine && (c<'0' || c>'9' && c<'A' || c>'Z' && c<'a' || c>'z')){
					quote = true;
				}
				if (c==' ' || c=='\f' || c=='\t'){
					quote = true;
				}
				for (int i=0; i<value.length(); i++){
					c = value.charAt(i);
					if (c==quoteChar || c==delimiterChar || c=='\n' || c=='\r'){
						quote = true;
					}
				}
				if (c==' ' || c=='\f' || c=='\t'){
					quote = true;
				}
			} else if (newLine) {
				// always quote an empty token that is the first
				// on the line, as it may be the only thing on the
				// line.  If it were not quoted in that case,
				// an empty line has no tokens.
				quote = true;
			}
			if (newLine){
				newLine = false;
			} else {
				out.write(delimiterChar);
			}
			if (quote){
				out.write(escapeAndQuote(value));
			} else {
				out.write(value);
			}
			if (autoFlush) flush();
		} catch (IOException iox){
			error = true;
			throw iox;
		}
	}

	/**
	 * Enclose the value in quotes and escape the quote
	 * and comma characters that are inside.
	 *
	 * @param value needs to be escaped and quoted
	 * @return the value, escaped and quoted.
	 *
	 * @since ostermillerutils 1.00.00
	 */
	private String escapeAndQuote(String value){
		int count = 2;
		for (int i=0; i<value.length(); i++){
			char c = value.charAt(i);
			switch(c){
				case '\n': case '\r': case '\\': {
					count ++;
				} break;
				default: {
					if (c == quoteChar){
						count++;
					}
				} break;
			}
		}
		StringBuffer sb = new StringBuffer(value.length() + count);
		sb.append(quoteChar);
		for (int i=0; i<value.length(); i++){
			char c = value.charAt(i);
			switch(c){
				case '\n': {
					sb.append("\\n");
				} break;
				case '\r': {
					sb.append("\\r");
				} break;
				case '\\': {
					sb.append("\\\\");
				} break;
				default: {
					if (c == quoteChar){
						sb.append("\\" + quoteChar);
					} else {
						sb.append(c);
					}
				}
			}
		}
		sb.append(quoteChar);
		return sb.toString();
	}

	/**
	 * Flush any data written out to underlying streams.
	 *
	 * @throws IOException if IO error occurs
	 * @since ostermillerutils 1.02.26
	 */
	public void flush() throws IOException {
		out.flush();
	}

	/**
	 * Close any underlying streams.
	 *
	 * @throws IOException if IO error occurs
	 * @since ostermillerutils 1.02.26
	 */
	public void close() throws IOException {
		out.close();
	}

	/**
	 * Flush the stream if it's not closed and check its error state.
	 * Errors are cumulative; once the stream encounters an error,
	 * this routine will return true on all successive calls.
	 *
	 * @return True if the print stream has encountered an error,
	 * either on the underlying output stream or during a format conversion.
	 *
	 * @since ostermillerutils 1.02.26
	 */
	public boolean checkError(){
		try {
			if (error) return true;
			flush();
			if (error) return true;
			if (out instanceof PrintWriter){
				error = ((PrintWriter)out).checkError();
			}
		} catch (IOException iox){
			error = true;
		}
		return error;
	}

	/**
	 * Set flushing behavior.  Iff set, a flush command
	 * will be issued to any underlying stream after each
	 * print or write command.
	 *
	 * @param autoFlush should auto flushing be enabled.
	 *
	 * @since ostermillerutils 1.02.26
	 */
	public void setAutoFlush(boolean autoFlush){
		this.autoFlush = autoFlush;
	}

	/**
	 * Set whether values printers should always be quoted, or
	 * whether the printer may, at its discretion, omit quotes
	 * around the value.
	 *
	 * @param alwaysQuote true if quotes should be used even when not strictly needed.
	 *
	 * @since ostermillerutils 1.02.26
	 */
	public void setAlwaysQuote(boolean alwaysQuote){
		this.alwaysQuote = alwaysQuote;
	}
}

