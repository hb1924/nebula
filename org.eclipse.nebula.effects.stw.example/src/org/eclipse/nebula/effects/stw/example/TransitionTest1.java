/*******************************************************************************
 * Copyright (c) 2010 Ahmed Mahran and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html 
 *
 * Contributors:
 *     Ahmed Mahran - initial API and implementation
 *******************************************************************************/

package org.eclipse.nebula.effects.stw.example;

import org.eclipse.nebula.effects.stw.TransitionManager; 
import org.eclipse.nebula.effects.stw.TransitionListener;
import org.eclipse.nebula.effects.stw.transitions.CubicRotationTransition;
import org.eclipse.nebula.effects.stw.transitions.SlideTransition;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;


/**
 * @author Ahmed Mahran (ahmahran@gmail.com)
 */
public class TransitionTest1 {

    private Shell sShell = null;
    private Composite comp1 = null;
    private Composite comp2 = null;
    private Composite comp3 = null;
    
    private Font fntTitle = null;

    public static void main(String[] args) {

        /* Before this is run, be sure to set up the launch configuration (Arguments->VM Arguments)
         * for the correct SWT library path in order to run with the SWT dlls. 
         * The dlls are located in the SWT plugin jar.  
         * For example, on Windows the Eclipse SWT 3.1 plugin jar is:
         *       installation_directory\plugins\org.eclipse.swt.win32_3.1.0.jar
         */
        Display display = Display.getDefault();
        TransitionTest1 thisClass = new TransitionTest1();
        thisClass.createSShell();
        thisClass.sShell.open();
        while (!thisClass.sShell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        display.dispose();
    }

    /**
     * This method initializes sShell
     */
    private void createSShell() {
        sShell = new Shell();
        sShell.setText("Transition Test 1");
        sShell.setSize(new Point(800, 600));
        sShell.setLocation(0, 0);
        sShell.setLayout(new FillLayout());
        
        TabFolder tf = new TabFolder(sShell, SWT.NONE);
        
        TabItem tbi1 = new TabItem(tf, SWT.NONE);
        tbi1.setText("Tab Item 1");
        tbi1.setControl(getComp1(tf));
        
        
        TabItem tbi2 = new TabItem(tf, SWT.NONE);
        tbi2.setText("Tab Item 2");
        tbi2.setControl(getComp2(tf));
        
        TabItem tbi3 = new TabItem(tf, SWT.NONE);
        tbi3.setText("Tab Item 3");
        tbi3.setControl(getComp3(tf));
        
        TransitionManager tm = new TransitionManager(tf);
        
        CubicRotationTransition crt = new CubicRotationTransition(tm, 60, 2000);
        tm.setTransition(crt);
        
        tm.addTransitionListener(new TransitionListener() {
            public void transitionFinished(TransitionManager transition) {
                System.out.println("End Of Transition! current item: " 
                        + transition.getTransitionable().getSelection());
            }
        });
        
    }
    
    private Font getTitleFont() {
        if(null == fntTitle) {
            fntTitle = new Font(null, "Verdana", 10, SWT.BOLD);
        }
        return fntTitle;
    }
    
    private Composite getComp1(Composite parent) {
        if(null == comp1) {
            comp1 = new Composite(parent, SWT.NONE);
            comp1.setLayout(new FormLayout());
            
            FormData fd;
            
            final Label cap = new Label(comp1, SWT.LEFT);
            cap.setText("\n  Tab Item 1");
            cap.setFont(getTitleFont());
            cap.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_BLACK));
            cap.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_GREEN));
            fd = new FormData();
            fd.top = new FormAttachment(0, 0);
            fd.height = 40;
            fd.left = new FormAttachment(0, 0);
            fd.right = new FormAttachment(100, 0);
            cap.setLayoutData(fd);
            
            final Label l1 = new Label(comp1, SWT.RIGHT);
            l1.setText("First Name :");
            fd = new FormData();
            fd.top = new FormAttachment(cap, 10);
            fd.height = 40;
            fd.right = new FormAttachment(0, 70);
            l1.setLayoutData(fd);
    
            final Label l2 = new Label(comp1, SWT.RIGHT);
            l2.setText("Last Name :");
            fd = new FormData();
            fd.top = new FormAttachment(l1, 5);
            fd.height = 40;
            fd.right = new FormAttachment(0, 70);
            l2.setLayoutData(fd);
    
            final Label l3 = new Label(comp1, SWT.RIGHT);
            l3.setText("Description :");
            fd = new FormData();
            fd.top = new FormAttachment(l2, 5);
            fd.height = 40;
            fd.right = new FormAttachment(0, 70);
            l3.setLayoutData(fd);
            
            final Text t1 = new Text(comp1, SWT.BORDER | SWT.SINGLE);
            fd = new FormData();
            fd.top = new FormAttachment(l1, 0, SWT.TOP);
            fd.left = new FormAttachment(l1, 10);
            fd.right = new FormAttachment(100, -10);
            t1.setLayoutData(fd);
            t1.setText("Ahmed");
    
            final Text t2 = new Text(comp1, SWT.BORDER | SWT.SINGLE);
            fd = new FormData();
            fd.top = new FormAttachment(l2, 0, SWT.TOP);
            fd.left = new FormAttachment(l2, 10);
            fd.right = new FormAttachment(100, -10);
            t2.setLayoutData(fd);
            t2.setText("Mohammed");
            
            final Text t3 = new Text(comp1, SWT.BORDER | SWT.MULTI);
            fd = new FormData();
            fd.top = new FormAttachment(l3, 0, SWT.TOP);
            fd.bottom = new FormAttachment(100, -10);
            fd.left = new FormAttachment(l3, 10);
            fd.right = new FormAttachment(100, -10);
            t3.setLayoutData(fd);
            t3.setText("How are you?\nAre you doning fine?\nI'm looking forward to see you soon ...");
        }
        return comp1;
    }
    
    private Composite getComp2(Composite parent) {
        if(null == comp2) {
            comp2 = new Composite(parent, SWT.NONE);
            comp2.setLayout(new FormLayout());
            
            FormData fd;
            
            final Label cap = new Label(comp2, SWT.LEFT);
            cap.setText("\n  Tab Item 2");
            cap.setFont(getTitleFont());
            cap.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GRAY));
            cap.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_MAGENTA));
            fd = new FormData();
            fd.top = new FormAttachment(0, 0);
            fd.height = 40;
            fd.left = new FormAttachment(0, 0);
            fd.right = new FormAttachment(100, 0);
            cap.setLayoutData(fd);
            
            final Label l1 = new Label(comp2, SWT.RIGHT);
            l1.setText("First Name :");
            fd = new FormData();
            fd.top = new FormAttachment(cap, 10);
            fd.height = 40;
            fd.right = new FormAttachment(0, 70);
            l1.setLayoutData(fd);
    
            final Label l2 = new Label(comp2, SWT.RIGHT);
            l2.setText("Last Name :");
            fd = new FormData();
            fd.top = new FormAttachment(l1, 5);
            fd.height = 40;
            fd.right = new FormAttachment(0, 70);
            l2.setLayoutData(fd);
    
            final Text t1 = new Text(comp2, SWT.BORDER | SWT.SINGLE);
            fd = new FormData();
            fd.top = new FormAttachment(l1, 0, SWT.TOP);
            fd.left = new FormAttachment(l1, 10);
            fd.right = new FormAttachment(100, -10);
            t1.setLayoutData(fd);
            t1.setText("Omar");
    
            final Text t2 = new Text(comp2, SWT.BORDER | SWT.SINGLE);
            fd = new FormData();
            fd.top = new FormAttachment(l2, 0, SWT.TOP);
            fd.left = new FormAttachment(l2, 10);
            fd.right = new FormAttachment(100, -10);
            t2.setLayoutData(fd);
            t2.setText("Ali");
            
            Group grp = new Group(comp2, SWT.NONE);
            grp.setLayout(new FormLayout());
            grp.setText("Group :");
            fd = new FormData();
            fd.top = new FormAttachment(l2, 5);
            fd.bottom = new FormAttachment(100, -10);
            fd.left = new FormAttachment(l2, 0, SWT.LEFT);
            fd.right = new FormAttachment(100, -10);
            grp.setLayoutData(fd);
        }
        return comp2;
    }
    
    private Composite getComp3(Composite parent) {
        if(null == comp3) {
            comp3 = new Composite(parent, SWT.NONE);
            comp3.setLayout(new FormLayout());
            
            FormData fd;
            
            final Label cap = new Label(comp3, SWT.LEFT);
            cap.setText("\n  Tab Item 3");
            cap.setFont(getTitleFont());
            cap.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_BLUE));
            cap.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
            fd = new FormData();
            fd.top = new FormAttachment(0, 0);
            fd.height = 40;
            fd.left = new FormAttachment(0, 0);
            fd.right = new FormAttachment(100, 0);
            cap.setLayoutData(fd);
            
            final Label l1 = new Label(comp3, SWT.RIGHT);
            l1.setText("First Name :");
            fd = new FormData();
            fd.top = new FormAttachment(cap, 10);
            fd.height = 40;
            fd.right = new FormAttachment(0, 70);
            l1.setLayoutData(fd);
    
            final Label l2 = new Label(comp3, SWT.RIGHT);
            l2.setText("Last Name :");
            fd = new FormData();
            fd.top = new FormAttachment(l1, 5);
            fd.height = 40;
            fd.right = new FormAttachment(0, 70);
            l2.setLayoutData(fd);
    
            final Text t1 = new Text(comp3, SWT.BORDER | SWT.SINGLE);
            fd = new FormData();
            fd.top = new FormAttachment(l1, 0, SWT.TOP);
            fd.left = new FormAttachment(l1, 10);
            fd.right = new FormAttachment(100, -10);
            t1.setLayoutData(fd);
            t1.setText("Othman");
    
            final Text t2 = new Text(comp3, SWT.BORDER | SWT.SINGLE);
            fd = new FormData();
            fd.top = new FormAttachment(l2, 0, SWT.TOP);
            fd.left = new FormAttachment(l2, 10);
            fd.right = new FormAttachment(100, -10);
            t2.setLayoutData(fd);
            t2.setText("Affan");
        }
        return comp3;
    }

}