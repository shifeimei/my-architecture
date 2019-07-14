package com.stx.core.editor;

/**
 * @author tongxiang.stx
 * @date 2019/07/14
 */

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.text.*;

//简单的文本编辑器

public class Editor extends JFrame {
    public JTextPane textPane = new JTextPane(); //文本窗格d，编辑窗口
    public JFileChooser filechooser = new JFileChooser(); //文件选择器
    public JTable table = new JTable(); //table

    public Editor() {
        super("简记");

        Action[] actions =            //菜单项的各种功能
            {
                new NewAction(),
                new OpenAction(),
                new SaveAction(),
                new CutAction(),
                new CopyAction(),
                new PasteAction(),
                new AboutAction(),
                new ExitAction(),
                new HelpAction(),
                new TableAction()
            };
        setJMenuBar(createJMenuBar(actions));        //根据actions创建菜单栏
        Container container = getContentPane();
        container.add(textPane, BorderLayout.CENTER);
        container.add(table);

        setSize(1300, 1500);
        setVisible(true);
        //	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JMenuBar createJMenuBar(Action[] actions)    //创建菜单栏的函数
    {
        JMenuBar menubar = new JMenuBar();
        JMenu menuFile = new JMenu("文件(F)");
        JMenu menuEdit = new JMenu("编辑(E)");
        JMenu menuAbout = new JMenu("帮助(H)");
        JMenu menuTable = new JMenu("表格(T)");
        menuFile.add(new JMenuItem(actions[0]));
        menuFile.add(new JMenuItem(actions[1]));
        menuFile.add(new JMenuItem(actions[2]));
        menuFile.add(new JMenuItem(actions[7]));
        menuEdit.add(new JMenuItem(actions[3]));
        menuEdit.add(new JMenuItem(actions[4]));
        menuEdit.add(new JMenuItem(actions[5]));
        menuAbout.add(new JMenuItem(actions[6]));
        menuAbout.add(new JMenuItem(actions[8]));
        menuTable.add(new JMenuItem(actions[9]));
        menubar.add(menuFile);
        menubar.add(menuEdit);
        menubar.add(menuAbout);
        menubar.add(menuTable);
        return menubar;
    }

    class NewAction extends AbstractAction        //新建
    {
        public NewAction() {
            super("新建(N)     Ctrl+N");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            textPane.setDocument(new DefaultStyledDocument());
        }
    }

    class OpenAction extends AbstractAction        //打开
    {
        public OpenAction() {
            super("打开(O)     Ctrl+O");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int i = filechooser.showOpenDialog(Editor.this);            //显示打开文件对话框
            if (i == JFileChooser.APPROVE_OPTION)            //点击对话框打开选项
            {
                File f = filechooser.getSelectedFile();    //得到选择的文件
                try {
                    InputStream is = new FileInputStream(f);
                    textPane.read(is, "d");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    class SaveAction extends AbstractAction        //保存
    {
        public SaveAction() {
            super("保存(S)     Ctrl+S");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int i = filechooser.showSaveDialog(Editor.this);
            if (i == JFileChooser.APPROVE_OPTION) {
                File f = filechooser.getSelectedFile();
                try {
                    FileOutputStream out = new FileOutputStream(f);
                    out.write(textPane.getText().getBytes());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    class ExitAction extends AbstractAction        //退出
    {
        public ExitAction() {
            super("退出(X)");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }

    class CutAction extends AbstractAction        //剪切
    {
        public CutAction() {
            super("剪切(T)     Ctrl+X");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            textPane.cut();
        }
    }

    class CopyAction extends AbstractAction        //复制
    {
        public CopyAction() {
            super("复制(C)     Ctrl+C");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            textPane.copy();
        }
    }

    class PasteAction extends AbstractAction        //粘贴
    {
        public PasteAction() {
            super("粘贴(P)     Ctrl+V");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            textPane.paste();
        }
    }

    class AboutAction extends AbstractAction {
        public AboutAction() {
            super("关于简记(A)");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(Editor.this, "记事本的一些基本功能", "关于", JOptionPane.PLAIN_MESSAGE);
        }
    }

    class HelpAction extends AbstractAction {
        public HelpAction() {
            super("联系开发者");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(Editor.this, "xxx", "开发者邮箱", JOptionPane.PLAIN_MESSAGE);
        }
    }

    class TableAction extends AbstractAction {
        public TableAction() {
            super("表格");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            table.setVisible(true);
            JOptionPane.showMessageDialog(Editor.this, "表格", "表格", JOptionPane.PLAIN_MESSAGE);
            table.addColumn(new TableColumn());
            table.addColumn(new TableColumn());
        }
    }

    public static void main(String[] args) throws Exception{
        final Object object = new Object();
        URL uri = new URL("http://localhost:8080");
        URLConnection connection = uri.openConnection();

        //new Editor();
        int b =0;
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    synchronized (object) {
                        object.notifyAll();
                    }
                }catch (Exception e){e.printStackTrace();}

            }
        });
        t1.setName("t1");
        t1.start();
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("t2 call t1 start");
                    //t1.join();
                    synchronized (object) {
                        object.wait();
                    }
                    System.out.println("t2 call t1 finish");
                } catch (Exception w) {
                    w.printStackTrace();
                }

            }
        });
        t2.setName("t2");
        t2.start();
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("t3 call t1 start");
                    //t1.join();
                    Thread.sleep(3000);
                    synchronized (object) {
                        object.wait();
                    }
                    System.out.println("t3 call t1 finish");
                } catch (Exception w) {
                    w.printStackTrace();
                }
            }
        });
        t3.setName("t3");
        t3.start();
        int a =0;
    }
}