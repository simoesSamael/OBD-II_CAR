/*
 * (C) Copyright 2015 by fr3ts0n <erwin.scheuch-heilig@gmx.at>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation; either version 2 of
 * the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston,
 * MA 02111-1307 USA
 */

package com.fr3ts0n.ecu.gui.application;

import com.fr3ts0n.common.UTF8Bundle;
import com.fr3ts0n.common.UTF8Control;
import com.fr3ts0n.ecu.EcuDataItem;
import com.fr3ts0n.ecu.EcuDataPv;
import com.fr3ts0n.ecu.prot.obd.ElmProt;
import com.fr3ts0n.ecu.prot.obd.ObdProt;
import com.fr3ts0n.prot.gui.SerialHandler;
import com.fr3ts0n.pvs.PvChangeEvent;
import com.fr3ts0n.pvs.PvChangeListener;
import com.fr3ts0n.pvs.PvList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


/**
 * Main application frame for OBD com.fr3ts0n.test application
 *
 * @author erwin
 */
public class ObdTestFrame extends javax.swing.JFrame
	implements PropertyChangeListener, PvChangeListener
{
	/**
	 *
	 */
	private static final long serialVersionUID = 3393967156524083209L;
	/** Program version string */
	private static final String version = "Version 0.9.7";
	private static final String copyright = "Copyright (C) 2007-2009 Erwin Scheuch-Heilig";

    /** Initialize UTF8 resource bundle */
    static UTF8Bundle res = new UTF8Bundle(new UTF8Control());

    /** icons */
	public ImageIcon icoCar = new javax.swing.ImageIcon(getClass().getResource("/com/fr3ts0n/ecu/gui/res/SUNFIRE.png"));

	/** protocol handler */
	private static final ElmProt prt = new ElmProt();
	/** Serial communication handler */
	private static final SerialHandler ser = new SerialHandler();

	/** is this a simulation, or the real world? */
	static boolean isSimulation = false;

	/**
	 * Action listener to handle read/clear code actions
	 */
	private final ActionListener hdlrCodeButtons = new ActionListener()
	{

		public void actionPerformed(ActionEvent e)
		{
			// if source is not defined, ignore event
			if (e.getSource() == null) return;

			if (e.getActionCommand().equals("ReadCodes"))
			{
				prt.setService(ElmProt.OBD_SVC_READ_CODES);
			} else if (e.getActionCommand().equals("ReadPending"))
			{
				prt.setService(ElmProt.OBD_SVC_PENDINGCODES);
			} else if (e.getActionCommand().equals("ReadPermanent"))
			{
				prt.setService(ElmProt.OBD_SVC_PERMACODES);
			} else if (e.getActionCommand().equals("ClearCodes"))
			{
				prt.setService(ElmProt.OBD_SVC_CLEAR_CODES);
			}
		}
	};

	/** Creates new form ObdTestFrame */
	private ObdTestFrame()
	{
		ObdProt.VidPvs.addPvChangeListener(this);
		// set up serial handler and protocol drivers
		ser.setMessageHandler(prt);
		prt.addTelegramWriter(ser);
		initComponents();
		// panAbout.setText(about);
		panObdData.setPidPvs(ObdProt.PidPvs);
		panObdFreezeFrame.setPidPvs(ObdProt.PidPvs);
		panCanData.setPidPvs(ElmProt.canProt.CanPvs);
		panCanData.setTitle("CAN Data Graph");
		panObdDtc.setTcList(ObdProt.tCodes);
		panObdDtc.addActionListener(hdlrCodeButtons);
	/* handle number of DTC changes */
		prt.addPropertyChangeListener(panObdDtc);
    /* handle protocol status changes */
		prt.addPropertyChangeListener(this);
	}

	/**
	 * This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	@SuppressWarnings({"rawtypes", "unchecked"})
	private void initComponents()
	{
		java.awt.GridBagConstraints gridBagConstraints;

		fChoose = new javax.swing.JFileChooser();
		tabMain = new javax.swing.JTabbedPane();
		javax.swing.JPanel panStart = new javax.swing.JPanel();
		javax.swing.JLabel lblTitle = new javax.swing.JLabel();
		javax.swing.JLabel lblFooter = new javax.swing.JLabel();
		javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
		TblVehIDs = new com.fr3ts0n.pvs.gui.PvTable(ObdProt.VidPvs);
		javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
		panObdDtc = new com.fr3ts0n.ecu.gui.application.ObdDtcPanel();
		panObdFreezeFrame = new com.fr3ts0n.ecu.gui.application.ObdDataPanel();
		panObdData = new com.fr3ts0n.ecu.gui.application.ObdDataPanel();
		panCanData = new com.fr3ts0n.ecu.gui.application.ObdDataPanel();
		javax.swing.JPanel panFooter = new javax.swing.JPanel();
		lblStatus = new javax.swing.JLabel();
		cbCnvSystem = new javax.swing.JComboBox();
		cbProtocol = new javax.swing.JComboBox();
		javax.swing.JPanel panHeader = new javax.swing.JPanel();
		javax.swing.JMenuBar mbMain = new javax.swing.JMenuBar();
		javax.swing.JMenu mnuFile = new javax.swing.JMenu();
		miLoad = new javax.swing.JMenuItem();
		miSave = new javax.swing.JMenuItem();
		javax.swing.JMenu mnuComm = new javax.swing.JMenu();
		miCommConfigure = new javax.swing.JMenuItem();
		miCommInit = new javax.swing.JMenuItem();
		miCommStart = new javax.swing.JMenuItem();
		miCommStop = new javax.swing.JMenuItem();

		FormListener formListener = new FormListener();

		fChoose.setFileFilter(new ObdFileFilter());
		fChoose.setFileSelectionMode(javax.swing.JFileChooser.FILES_AND_DIRECTORIES);

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("JObdScanTool");

		tabMain.setFont(new java.awt.Font("Dialog", 0, 10));
		tabMain.setPreferredSize(new java.awt.Dimension(520, 350));
		tabMain.addChangeListener(formListener);

		panStart.setBackground(new java.awt.Color(255, 255, 255));
		panStart.setLayout(new java.awt.BorderLayout());

		lblTitle.setFont(new java.awt.Font("Dialog", 1, 18));
		lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblTitle.setText("Java OBD ScanTool");
		panStart.add(lblTitle, java.awt.BorderLayout.NORTH);

		lblFooter.setFont(new java.awt.Font("Dialog", 0, 10));
		lblFooter.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblFooter.setText(copyright);
		panStart.add(lblFooter, java.awt.BorderLayout.SOUTH);

		jPanel1.setBackground(new java.awt.Color(255, 255, 255));
		jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 10, 20, 10));
		jPanel1.setLayout(new java.awt.GridLayout(2, 0));

		TblVehIDs.setName("TblVids"); // NOI18N
		TblVehIDs.setOpaque(false);
		TblVehIDs.setRowHeight(20);
		TblVehIDs.setShowGrid(false);
		jPanel1.add(TblVehIDs);

		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/fr3ts0n/ecu/gui/res/SUNFIRE.png"))); // NOI18N
		jPanel1.add(jLabel1);

		panStart.add(jPanel1, java.awt.BorderLayout.CENTER);

		tabMain.addTab("About", panStart);
		tabMain.addTab("Fault Codes", panObdDtc);
		tabMain.addTab("Freeze Frames", panObdFreezeFrame);
		tabMain.addTab("OBD-Data", panObdData);
		tabMain.addTab("CAN-Monitor", panCanData);

		getContentPane().add(tabMain, java.awt.BorderLayout.CENTER);

		panFooter.setLayout(new java.awt.GridBagLayout());

		lblStatus.setFont(new java.awt.Font("Dialog", 0, 10));
		lblStatus.setText(version);
		lblStatus.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED), javax.swing.BorderFactory.createEmptyBorder(1, 3, 1, 3)));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		panFooter.add(lblStatus, gridBagConstraints);

		cbCnvSystem.setFont(new java.awt.Font("Dialog", 0, 10));
		cbCnvSystem.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Metric", "Imperial"}));
		cbCnvSystem.setToolTipText("Select conversion system");
		cbCnvSystem.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
		cbCnvSystem.setPreferredSize(new java.awt.Dimension(71, 23));
		cbCnvSystem.addItemListener(formListener);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		panFooter.add(cbCnvSystem, gridBagConstraints);

		cbProtocol.setFont(new java.awt.Font("Dialog", 0, 10));
		cbProtocol.setModel(new javax.swing.DefaultComboBoxModel(ElmProt.PROT.values()));
		cbProtocol.setToolTipText("Select communication protocol");
		cbProtocol.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
		cbProtocol.addActionListener(formListener);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		panFooter.add(cbProtocol, gridBagConstraints);

		getContentPane().add(panFooter, java.awt.BorderLayout.SOUTH);

		panHeader.setLayout(new java.awt.GridLayout(1, 0));
		getContentPane().add(panHeader, java.awt.BorderLayout.NORTH);

		mbMain.setFont(new java.awt.Font("Dialog", 0, 10));

		mnuFile.setMnemonic('F');
		mnuFile.setText("File");
		mnuFile.setFont(new java.awt.Font("Dialog", 0, 10));

		miLoad.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
		miLoad.setFont(new java.awt.Font("Dialog", 0, 10));
		miLoad.setMnemonic('L');
		miLoad.setText("Load measurement");
		miLoad.addActionListener(formListener);
		mnuFile.add(miLoad);

		miSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
		miSave.setFont(new java.awt.Font("Dialog", 0, 10));
		miSave.setMnemonic('S');
		miSave.setText("Save measurement");
		miSave.addActionListener(formListener);
		mnuFile.add(miSave);

		mbMain.add(mnuFile);

		mnuComm.setMnemonic('C');
		mnuComm.setText("Communication");
		mnuComm.setFont(new java.awt.Font("Dialog", 0, 10));

		miCommConfigure.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F8, 0));
		miCommConfigure.setFont(new java.awt.Font("Dialog", 0, 10));
		miCommConfigure.setMnemonic('C');
		miCommConfigure.setText("Port Configuration...");
		miCommConfigure.addActionListener(formListener);
		mnuComm.add(miCommConfigure);

		miCommInit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
		miCommInit.setFont(new java.awt.Font("Dialog", 0, 10));
		miCommInit.setMnemonic('I');
		miCommInit.setText("Initialize");
		miCommInit.addActionListener(formListener);
		mnuComm.add(miCommInit);

		miCommStart.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F11, 0));
		miCommStart.setFont(new java.awt.Font("Dialog", 0, 10));
		miCommStart.setMnemonic('S');
		miCommStart.setText("Start");
		miCommStart.addActionListener(formListener);
		mnuComm.add(miCommStart);

		miCommStop.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F9, 0));
		miCommStop.setFont(new java.awt.Font("Dialog", 0, 10));
		miCommStop.setMnemonic('p');
		miCommStop.setText("Stop");
		miCommStop.addActionListener(formListener);
		mnuComm.add(miCommStop);

		mbMain.add(mnuComm);

		setJMenuBar(mbMain);

		pack();
	}

	// Code for dispatching events from components to event handlers.

	private class FormListener implements java.awt.event.ActionListener, java.awt.event.ItemListener, javax.swing.event.ChangeListener
	{
		FormListener()
		{
		}

		public void actionPerformed(java.awt.event.ActionEvent evt)
		{
			if (evt.getSource() == cbProtocol)
			{
				ObdTestFrame.this.cbProtocolActionPerformed();
			} else if (evt.getSource() == miLoad)
			{
				ObdTestFrame.this.miLoadActionPerformed();
			} else if (evt.getSource() == miSave)
			{
				ObdTestFrame.this.miSaveActionPerformed();
			} else if (evt.getSource() == miCommConfigure)
			{
				ObdTestFrame.this.miCommConfigureActionPerformed();
			} else if (evt.getSource() == miCommInit)
			{
				ObdTestFrame.this.miCommInitActionPerformed();
			} else if (evt.getSource() == miCommStart)
			{
				ObdTestFrame.this.miCommStartActionPerformed();
			} else if (evt.getSource() == miCommStop)
			{
				ObdTestFrame.this.miCommStopActionPerformed();
			}
		}

		public void itemStateChanged(java.awt.event.ItemEvent evt)
		{
			if (evt.getSource() == cbCnvSystem)
			{
				ObdTestFrame.this.cbCnvSystemItemStateChanged();
			}
		}

		public void stateChanged(javax.swing.event.ChangeEvent evt)
		{
			if (evt.getSource() == tabMain)
			{
				ObdTestFrame.this.tabMainStateChanged();
			}
		}
	}// </editor-fold>//GEN-END:initComponents

	private void miCommConfigureActionPerformed()//GEN-FIRST:event_miCommConfigureActionPerformed
	{//GEN-HEADEREND:event_miCommConfigureActionPerformed
		ser.configure();
	}//GEN-LAST:event_miCommConfigureActionPerformed

	private void miCommStopActionPerformed()//GEN-FIRST:event_miCommStopActionPerformed
	{//GEN-HEADEREND:event_miCommStopActionPerformed
		// switch off PID's supported'
		prt.setService(ElmProt.OBD_SVC_NONE);
	}//GEN-LAST:event_miCommStopActionPerformed

	private void miCommStartActionPerformed()//GEN-FIRST:event_miCommStartActionPerformed
	{//GEN-HEADEREND:event_miCommStartActionPerformed
		// request OBD service for selected Tab
		requestServiceForSelectedTab();
	}//GEN-LAST:event_miCommStartActionPerformed

	private void miCommInitActionPerformed()//GEN-FIRST:event_miCommInitActionPerformed
	{//GEN-HEADEREND:event_miCommInitActionPerformed
		prt.sendCommand(ElmProt.CMD.RESET, 0);
	}//GEN-LAST:event_miCommInitActionPerformed

	private void miSaveActionPerformed()//GEN-FIRST:event_miSaveActionPerformed
	{//GEN-HEADEREND:event_miSaveActionPerformed
		if (fChoose.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
		{
			File file = fChoose.getSelectedFile();
			// ask for overwrite existing file
			if (!file.exists()
				|| JOptionPane.showConfirmDialog(this,
				"Really want to overwrite " + file.getPath(),
				"File overwrite",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
				try
				{

					FileOutputStream out = new FileOutputStream(file);
					ObjectOutputStream oOut = new ObjectOutputStream(out);
        /* remember current measurement page for loading again */
					Integer currPage = Integer.valueOf(tabMain.getSelectedIndex());
					oOut.writeObject(currPage);
        /* save the data */
					oOut.writeObject(ObdProt.PidPvs);
					oOut.writeObject(ElmProt.canProt.CanPvs);
					oOut.writeObject(panObdData.selPids);
					oOut.writeObject(panCanData.selPids);
					oOut.close();
				} catch (IOException ex)
				{
					ex.printStackTrace();
					JOptionPane.showMessageDialog(this,
						ex.getLocalizedMessage(),
						"Save ERROR",
						JOptionPane.ERROR_MESSAGE);
				}
		}
	}//GEN-LAST:event_miSaveActionPerformed

	@SuppressWarnings({"unchecked", "rawtypes"})
	private void miLoadActionPerformed()//GEN-FIRST:event_miLoadActionPerformed
	{//GEN-HEADEREND:event_miLoadActionPerformed
		if (fChoose.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
		{
			File file = fChoose.getSelectedFile();
			try
			{
				FileInputStream in = new FileInputStream(file);
				ObjectInputStream oIn = new ObjectInputStream(in);
        /* ensure that measurement page is activated
           to avoid deletion of loaded data afterwards */
				Integer currPage = (Integer) oIn.readObject();
				tabMain.setSelectedIndex(currPage);
        /* read in the data */
				ObdProt.PidPvs = (PvList) oIn.readObject();
				ElmProt.canProt.CanPvs = (PvList) oIn.readObject();
				// re-setup data connection
				panObdData.setPidPvs(ObdProt.PidPvs);
				panCanData.setPidPvs(ElmProt.canProt.CanPvs);
				// read measurement history
				panObdData.selPids = (HashMap) oIn.readObject();
				panCanData.selPids = (HashMap) oIn.readObject();
				oIn.close();
			} catch (Exception ex)
			{
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this,
					ex.getLocalizedMessage(),
					"Load ERROR",
					JOptionPane.ERROR_MESSAGE);
			}
		}
	}//GEN-LAST:event_miLoadActionPerformed

	private void cbProtocolActionPerformed()//GEN-FIRST:event_cbProtocolActionPerformed
	{//GEN-HEADEREND:event_cbProtocolActionPerformed
		prt.sendCommand(ElmProt.CMD.SETPROTAUTO, cbProtocol.getSelectedIndex());
	}//GEN-LAST:event_cbProtocolActionPerformed

	/**
	 * handle change of conversion system
	 */
	private void cbCnvSystemItemStateChanged()//GEN-FIRST:event_cbCnvSystemItemStateChanged
	{//GEN-HEADEREND:event_cbCnvSystemItemStateChanged
		// set new conversion system
		EcuDataItem.cnvSystem = cbCnvSystem.getSelectedIndex();

		// update currently selected display
		switch (tabMain.getSelectedIndex())
		{
			case 2:
				panObdFreezeFrame.updateAllTableRows(EcuDataPv.FID_UNITS);
				break;

			case 3:
				panObdData.updateAllTableRows(EcuDataPv.FID_UNITS);
				break;

			case 4:
				panCanData.updateAllTableRows(EcuDataPv.FID_UNITS);
				break;

			default:
				// intentionally do nothing ...
		}
	}//GEN-LAST:event_cbCnvSystemItemStateChanged

	/**
	 * update form/tab selection
	 */
	private void tabMainStateChanged()//GEN-FIRST:event_tabMainStateChanged
	{//GEN-HEADEREND:event_tabMainStateChanged
		// request OBD service for selected Tab
		requestServiceForSelectedTab();
		updateColumnWidths();
	}//GEN-LAST:event_tabMainStateChanged

	/**
	 * request corresponding OBD service for selected Tab
	 */
	private void requestServiceForSelectedTab()
	{
		// handle page change ...
		switch (tabMain.getSelectedIndex())
		{
			case 0: // About panel
				// switch off PID's supported'
				prt.setService(ElmProt.OBD_SVC_VEH_INFO);
				break;

			case 1: // Trouble code panel
				// we don't set any service here
				// since service is selected by buttons
				prt.setService(ElmProt.OBD_SVC_NONE);
				break;

			case 2: // freeze frame panel
				// write data initialisation telegram
				prt.setService(ElmProt.OBD_SVC_FREEZEFRAME);
				break;

			case 3: // data item panel
				// write data initialisation telegram
				prt.setService(ElmProt.OBD_SVC_DATA);
				break;

			case 4: // CAN monitor panel
				// write data initialisation telegram
				prt.setService(ElmProt.OBD_SVC_CAN_MONITOR);
				break;

			default:
				// switch off PID's supported'
				prt.setService(ElmProt.OBD_SVC_NONE);
				// do nothing
		}
	}

	/**
	 * The main routine
	 *
	 * @param args the command line arguments
	 */
	public static void main(String args[])
	{
		ObdTestFrame frm = new ObdTestFrame();
		frm.setVisible(true);
		// command line argument is the com port
		if (args.length > 0)
		{
			try
			{
				ser.setDeviceName(args[0]);
			} catch (Exception ex)
			{
				JOptionPane.showMessageDialog(frm,
					ex,
					"Communication error",
					JOptionPane.ERROR_MESSAGE);
			}
			ser.start();
		} else
		{
			// without parameter we do internal telegram simulation ...
			Thread sim = new Thread(prt);
			sim.start();
		}
	}

	/**
	 * Property change listener to ELM-Protocol
	 *
	 * @param evt the property change event to be handled
	 */
	public void propertyChange(PropertyChangeEvent evt)
	{
    /* handle protocol status changes */
		if (evt.getPropertyName().equals("status"))
		{
			lblStatus.setText(evt.getNewValue().toString());
		}
	}

	/**
	 * update the column widths of data table
	 */
	private void updateColumnWidths()
	{
		if (TblVehIDs.getRowCount() >= 1)
		{
			/** set column sizes here, since this only works with inserted data */
			TblVehIDs.getColumn(EcuDataPv.FIELDS[EcuDataPv.FID_PID]).setPreferredWidth(20);
			TblVehIDs.getColumn(EcuDataPv.FIELDS[EcuDataPv.FID_OFS]).setPreferredWidth(20);
			TblVehIDs.getColumn(EcuDataPv.FIELDS[EcuDataPv.FID_DESCRIPT]).setPreferredWidth(350);
			TblVehIDs.getColumn(EcuDataPv.FIELDS[EcuDataPv.FID_VALUE]).setPreferredWidth(350);
		}
	}

	/**
	 * handle changes in the process var(s)
	 *
	 * @param event Process var event to be handled
	 */
	public void pvChanged(PvChangeEvent event)
	{
		// update table column widths
		updateColumnWidths();
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private com.fr3ts0n.pvs.gui.PvTable TblVehIDs;
	@SuppressWarnings("rawtypes")
	private javax.swing.JComboBox cbCnvSystem;
	@SuppressWarnings("rawtypes")
	private javax.swing.JComboBox cbProtocol;
	private javax.swing.JFileChooser fChoose;
	private javax.swing.JLabel lblStatus;
	private javax.swing.JMenuItem miCommConfigure;
	private javax.swing.JMenuItem miCommInit;
	private javax.swing.JMenuItem miCommStart;
	private javax.swing.JMenuItem miCommStop;
	private javax.swing.JMenuItem miLoad;
	private javax.swing.JMenuItem miSave;
	private com.fr3ts0n.ecu.gui.application.ObdDataPanel panCanData;
	private com.fr3ts0n.ecu.gui.application.ObdDataPanel panObdData;
	private com.fr3ts0n.ecu.gui.application.ObdDtcPanel panObdDtc;
	private com.fr3ts0n.ecu.gui.application.ObdDataPanel panObdFreezeFrame;
	private javax.swing.JTabbedPane tabMain;
	// End of variables declaration//GEN-END:variables

}
