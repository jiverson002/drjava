/*BEGIN_COPYRIGHT_BLOCK
 *
 * This file is part of DrJava.  Download the current version of this project:
 * http://sourceforge.net/projects/drjava/ or http://www.drjava.org/
 *
 * DrJava Open Source License
 *
 * Copyright (C) 2001-2003 JavaPLT group at Rice University (javaplt@rice.edu)
 * All rights reserved.
 *
 * Developed by:   Java Programming Languages Team
 *                 Rice University
 *                 http://www.cs.rice.edu/~javaplt/
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the "Software"),
 * to deal with the Software without restriction, including without
 * limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to
 * whom the Software is furnished to do so, subject to the following
 * conditions:
 *
 *     - Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimers.
 *     - Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimers in the
 *       documentation and/or other materials provided with the distribution.
 *     - Neither the names of DrJava, the JavaPLT, Rice University, nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this Software without specific prior written permission.
 *     - Products derived from this software may not be called "DrJava" nor
 *       use the term "DrJava" as part of their names without prior written
 *       permission from the JavaPLT group.  For permission, write to
 *       javaplt@rice.edu.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE CONTRIBUTORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS WITH THE SOFTWARE.
 *
END_COPYRIGHT_BLOCK*/

package edu.rice.cs.drjava.ui;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;
import javax.swing.border.MatteBorder;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.awt.*;
import java.awt.print.*;
import java.awt.image.*;
import java.net.*;
import java.lang.reflect.Method;

import edu.rice.cs.drjava.model.*;

import edu.rice.cs.util.text.ConsoleInterface;

/**
 * DrJava's print preview window for a console document (interactions or console)
 * @version $Id$
 */
public class PreviewConsoleFrame extends PreviewFrame {

  private ConsoleInterface _document;

  /**
   * Contructs a new PreviewConsoleFrame using a parent model and a MainFrame. The boolean determines whether
   * the document to be printed is an interactions document.
   */
  public PreviewConsoleFrame(SingleDisplayModel model, MainFrame mainFrame, boolean interactions)
    throws IllegalStateException {
    super(model, mainFrame, interactions);
  }
  
  
   /** Sets up the document to be displayed and returns the Pageable object that allows display by pages
   * 
   *  @param model the current display model
   *  @param interactions whether the document is an interactions document
   *  
   *  @return a Pageable object that allows the document to be displayed by pages
   */
  protected Pageable setUpDocument(SingleDisplayModel model, boolean interactions) {
    if (interactions) _document = model.getInteractionsDocument();
    else _document = model.getConsoleDocument();
    return _document.getPageable();
  }

  protected void _print() {
    try {
      _document.print();
    }
    catch (PrinterException e) {
      _showError(e, "Print Error", "An error occured while printing.");
    }
  }
}