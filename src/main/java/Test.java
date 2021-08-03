import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.TableView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.BrokenBarrierException;

import static java.lang.Math.abs;


public class Test implements ActionListener {
    JFrame frame = new JFrame();
    JPanel rc = new JPanel();
    JLabel r = new JLabel();
    JLabel c = new JLabel();
    JTextField rVal = new JTextField(10);
    JButton enter = new JButton();
    JPanel pantab = new JPanel();
    DefaultTableModel first = new DefaultTableModel();
    JTable firstab = new JTable();
    JPanel forsp = new JPanel();
    JButton next = new JButton();
    JPanel ty = new JPanel();
    JScrollPane sp = new JScrollPane();
    DefaultTableModel sec = new DefaultTableModel();
    JTable sectab = new JTable();
    DefaultTableModel col = new DefaultTableModel();
    JTable coltab = new JTable();

    JPanel panRow = new JPanel();


    int[][] values = new int[1000][1000];

    Test() {
        frame.setTitle("Machine Problem 3.1");
        frame.setSize(900, 800);
        frame.setVisible(true);

        rc.setLayout(new FlowLayout());
        rc.setBackground(new Color(69, 98, 104));

        r.setText("Enter number of rows and Columns: ");
        r.setForeground(Color.WHITE);
        r.setFont(new Font("Calibri", Font.PLAIN, 16));

        rVal.setText("");

        enter.setText("ENTER");
        enter.addActionListener(this);


        pantab.setBackground(new Color(121, 163, 177));
        pantab.setLayout(new BoxLayout(pantab, BoxLayout.PAGE_AXIS));
        forsp.setBackground(new Color(208, 232, 242));
        ty.setBackground(Color.WHITE);
        ty.setLayout(new BoxLayout(ty, BoxLayout.PAGE_AXIS));

        next.setText("NEXT");
        next.addActionListener(this);


        frame.add(rc, BorderLayout.NORTH);
        rc.add(r);
        rc.add(rVal);
        rc.add(enter);
        frame.add(pantab, BorderLayout.WEST);
        frame.add(forsp, BorderLayout.CENTER);
        forsp.add(ty, BorderLayout.NORTH);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enter) {
            rc.updateUI();
            int nr;
            nr = Integer.valueOf(rVal.getText());
            first = new DefaultTableModel(nr, nr);
            firstab = new JTable(first);
            pantab.add(firstab);
            pantab.add(next);
            pantab.add(next, Box.createRigidArea(new Dimension(10, 10)));
        }
        if (e.getSource() == next) {
            int nr;
            nr = Integer.valueOf(rVal.getText());
            for (int i = 0; i < nr; i++) {
                for (int j = 0; j < nr; j++) {
                    String[][] cc = new String[nr][nr];
                    cc[i][j] = first.getValueAt(i, j).toString();
                    values[i][j] = Integer.parseInt(cc[i][j]);
                }
            }
            //RowMin(values); 
            //ColMin(values);
            ty.updateUI();
            sec = new DefaultTableModel(nr, nr);
            sectab = new JTable(sec);

            for (int i = 0; i < nr; i++) {
                for (int j = 0; j < nr; j++) {
                    sectab.setValueAt(values[i][j], i, j);
                    RowMin(values);
                }
            }
            sp = new JScrollPane(sectab, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            //RowMin(values);


            col = new DefaultTableModel(nr, nr);
            coltab = new JTable(col);
            for (int i = 0; i < nr; i++) {
                for (int j = 0; j < nr; j++) {
                    coltab.setValueAt(values[i][j], i, j);
                }
            }
            //penalty(values);
            sp = new JScrollPane(coltab, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            ColMin(values);
            //  sp.add(coltab);


            ty.add(sp);


        }

        /*if (e.getSource()==next){
            rc.updateUI();
            int nr, nc;
            nr = Integer.valueOf(rVal.getText());
            nc = Integer.valueOf(cVal.getText());
            sec = new DefaultTableModel(nc, nr);
            sectab = new JTable(sec);
            sp = new JScrollPane(sectab, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            ty.add(firstab);

         */
    }

    public void RowMin(int values[][]) {
        int min = Integer.MAX_VALUE;
        int nr = Integer.valueOf(rVal.getText());
        for (int i = 0; i < nr; i++) {
            min = Integer.MAX_VALUE;
            for (int j = 0; j < nr; j++) {
                if (values[i][j] < min && i != j) {
                    min = values[i][j];

                }
            }
            for (int j = 0; j < nr; j++) {
                if (i != j) {
                    values[i][j] = values[i][j] - min;
                }
            }
        }
    }

    public void ColMin(int values[][]) {
        int min = Integer.MAX_VALUE;
        int nr = Integer.valueOf(rVal.getText());
        for (int j = 0; j < nr; j++) {
            min = Integer.MAX_VALUE;
            for (int i = 0; i < nr; i++) {
                if (values[i][j] < min && i != j) {
                    min = values[i][j];

                }
            }
            for (int i = 0; i < nr; i++) {
                if (i != j) {
                    values[i][j] = values[i][j] - min;
                }
            }
        }
    }

    public void penalty(int values[][]) {
        int nr = Integer.valueOf(rVal.getText());
        int rmin = Integer.MAX_VALUE;
        int cmin = Integer.MAX_VALUE;
        //  int [][] penalties = new int[nr][nr];
        int penalty = Integer.MIN_VALUE;
        int hPenaltyRow=-1;
        int hPenaltyColumn=-1;
        for (int i = 0; i < nr; i++) {
            for (int j = 0; j < nr; j++) {
                if (i != j && values[i][j] == 0) {
                    for (int k = 0; k < nr; k++) {
                        if (i != k && j != k && cmin > values[i][k])
                            cmin = values[i][k];
                    }
                    for (int k = 0; k < nr; k++) {
                        if (j != k && i != k && rmin > values[k][j])
                            rmin = values[k][j];
                    }
                    if(abs(rmin-cmin) >  penalty) {
                        penalty = abs(rmin+cmin);
                        hPenaltyRow = i;
                        hPenaltyColumn = j;
                    }
                    // penalties [i][j] = abs(rmin-cmin);

                }
                cmin = Integer.MAX_VALUE;
                rmin = Integer.MAX_VALUE;
            }
        }
        System.out.println(hPenaltyRow);
        System.out.println(hPenaltyColumn);
        //((DefaultTableModel)sectab.getModel()).removeRow(hPenaltyRow);
        // sec = (DefaultTableModel)sectab.getModel();
        // sectab.getSelectedRow(2);
        // sec.removeRow(sectab.getSelectedRow());
        sec.removeRow(hPenaltyRow);
        TableColumn col = sectab.getColumnModel().getColumn(hPenaltyColumn);
        sectab.removeColumn(col);
    }
}
