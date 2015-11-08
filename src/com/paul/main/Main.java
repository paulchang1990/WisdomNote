/**
 * The MIT License (MIT)
 * 
 * Copyright (c) 2015 Paul Chang
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.paul.main;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 该类是程序的启动入口，该项目的主界面.
 * 
 * @author Paul Chang 2015-11-7
 * 
 */
public class Main {

	private static final String MAIN_FRAME_TITLE = "WisdomNote";
	private static Frame mainFrame;
	private static Panel mPanelContainer;
	private static Button mStoreBtn;
	private static Button mClearBtn;
	private static TextField mTitleText;
	private static Dialog mAlertDialog;
	private static Button mDialogConfirmBtn;
	private static Button mDialogCancelBtn;
	private static TextArea mDescText;
	private static TextArea mReferText;
	private static SimpleDateFormat mDateFormat;
	private static File mTargetFile;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Main main = new Main();
		String path = main.getClass().getProtectionDomain().getCodeSource()
				.getLocation().getPath();
		
		if(path.endsWith(".jar")){
			int lastIndexOf = path.lastIndexOf("/");
			path = path.substring(0, lastIndexOf);
		}
		mDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		mTargetFile = new File(path,"WisdomNote-"+mDateFormat.format(new Date())+".md");
		
		mainFrame = new Frame(MAIN_FRAME_TITLE);

		mainFrame.setLayout(new BorderLayout());
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;

		int mainFrameHeight = (int) (screenHeight * 0.3 + 0.5f);
		int mainFrameWidth = (int) (screenWidth / 5 + 0.5f);

		mainFrame.setLocation((int) (screenWidth * 1 / 2 + 0.5f),
				(int) (screenHeight * 1 / 5 + 0.5f));
		mainFrame.setSize(mainFrameWidth, mainFrameHeight);
		mainFrame.setResizable(false);

		mPanelContainer = new Panel(new GridLayout(3, 1));

		Label titleLabel = new Label("标题：", Label.LEFT);
		mTitleText = new TextField(40);
		Panel titlePanel = new Panel(new BorderLayout());
		titlePanel.add(titleLabel, BorderLayout.NORTH);
		titlePanel.add(mTitleText, BorderLayout.CENTER);

		Label descLabel = new Label("描述：", Label.LEFT);
		mDescText = new TextArea("", 3, 40, TextArea.SCROLLBARS_VERTICAL_ONLY);
		Panel descPanel = new Panel(new BorderLayout());
		descPanel.add(descLabel, BorderLayout.NORTH);
		descPanel.add(mDescText, BorderLayout.CENTER);

		Label referLabel = new Label("参考网站：", Label.LEFT);
		mReferText = new TextArea("", 3, 40, TextArea.SCROLLBARS_VERTICAL_ONLY);
		Panel referPanel = new Panel(new BorderLayout());
		referPanel.add(referLabel, BorderLayout.NORTH);
		referPanel.add(mReferText, BorderLayout.CENTER);

		Panel btnPanel = new Panel(new FlowLayout(FlowLayout.CENTER));
		mPanelContainer.add(titlePanel);
		mPanelContainer.add(descPanel);
		mPanelContainer.add(referPanel);

		mStoreBtn = new Button("存储");
		mClearBtn = new Button("清空");

		btnPanel.add(mStoreBtn);
		btnPanel.add(mClearBtn);

		mainFrame.add(mPanelContainer, BorderLayout.NORTH);
		mainFrame.add(btnPanel, BorderLayout.CENTER);

		mAlertDialog = new Dialog(mainFrame, "确认清空？", true);
		mAlertDialog.setLayout(new BorderLayout());
		mAlertDialog.setLocationRelativeTo(mainFrame);
		mAlertDialog.setLocation(mainFrame.getX() + mainFrameWidth / 4,
				mainFrame.getY() + mainFrameHeight / 2);
		mAlertDialog.setSize(mainFrameWidth / 2, mainFrameHeight / 3);
		Label msgLabel = new Label("确定要清空数据吗？");

		Panel dialogBtnPanel = new Panel();
		mDialogConfirmBtn = new Button("确认");
		mDialogCancelBtn = new Button("取消");

		dialogBtnPanel.add(mDialogConfirmBtn);
		dialogBtnPanel.add(mDialogCancelBtn);
		mAlertDialog.add(msgLabel, BorderLayout.NORTH);
		mAlertDialog.add(dialogBtnPanel, BorderLayout.CENTER);

		initEvent();

		mainFrame.setVisible(true);

	}

	/**
	 * 初始化所有组件的事件.
	 */
	private static void initEvent() {
		mainFrame.addWindowListener(new WindowAdapter() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent
			 * )
			 */
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);

				System.exit(0);
			}
		});

		mStoreBtn.addMouseListener(new MouseAdapter() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent
			 * )
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO store text content
				String title = mTitleText.getText();
				String desc = mDescText.getText();
				String refer = mReferText.getText();
//				System.out.println(title+"\r\n"+desc+"\r\n"+refer);
				if (title == null || title.trim().length() == 0) {
					return;
				}

				if (desc == null || desc.trim().length() == 0) {
					desc = "无";
				}
				if (refer == null || refer.trim().length() == 0) {
					refer = "无";
				}
				
				String[] refers = refer.split("\r\n");
				refer = "";
				int temp = 1;
				for (int i = 0; i < refers.length; i++) {
					if(refers[i].trim().length() != 0){
						refer += " [参考网址"+ temp++ +"]("+refers[i]+")\r\n";
					}
				}
				
				String[] descs = desc.split("\r\n");
				desc = "";
				for (int i = 0; i < descs.length; i++) {
					if(descs[i].trim().length() != 0){
						desc += "> "+descs[i]+"\r\n\r\n";
					}
				}
				
				String data = "--- \r\n"+"#### "+title+"\r\n"+desc+refer;
				if(FileUtils.writeDataToFile(data, mTargetFile)){
					clearAllText();
				}
			}

		});

		mClearBtn.addMouseListener(new MouseAdapter() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent
			 * )
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				mAlertDialog.setVisible(true);
			}

		});

		mAlertDialog.addWindowListener(new WindowAdapter() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent
			 * )
			 */
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				mAlertDialog.dispose();
			}
		});

		mDialogCancelBtn.addMouseListener(new MouseAdapter() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent
			 * )
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				mAlertDialog.dispose();
			}
		});

		mDialogConfirmBtn.addMouseListener(new MouseAdapter() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent
			 * )
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				clearAllText();
				mAlertDialog.dispose();
			}
		});

	}

	private static void clearAllText() {
		mTitleText.setText(" ");// this is a jdk bug ,must use this first if you
								// want to clear the Text content.
		mTitleText.setText("");
		mDescText.setText(" ");
		mDescText.setText("");
		mReferText.setText(" ");
		mReferText.setText("");
	}
}
