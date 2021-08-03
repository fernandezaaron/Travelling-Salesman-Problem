import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TSP extends JFrame {

    JScrollPane mother, scrollcodesim;
    JPanel container, inside, upper, codesimulation, center, south, upper1, upper2, codesimulation1;
    JTable[] table1 = new JTable[1000];
    JTable[] table2 = new JTable[1000];
    JTable[] table3 = new JTable[1000];
    JTable[] table4 = new JTable[1000];
    JTable[] table5 = new JTable[1000];
    DefaultTableModel[] dtm1 = new DefaultTableModel[1000];
    DefaultTableModel[] dtm2 = new DefaultTableModel[1000];
    DefaultTableModel[] dtm3 = new DefaultTableModel[1000];
    DefaultTableModel[] dtm4 = new DefaultTableModel[1000];
    DefaultTableModel[] dtm5 = new DefaultTableModel[1000];
    JTextField[] label1 = new JTextField[1000];
    JTextField[] label2 = new JTextField[1000];
    JTextField[] label3 = new JTextField[1000];
    JTextField[] label4 = new JTextField[1000];
    JTextField[] label5 = new JTextField[1000];
    JTextField[] path = new JTextField[1000];
    JScrollPane[] scroll1 = new JScrollPane[1000];
    JScrollPane[] scroll2 = new JScrollPane[1000];
    JScrollPane[] scroll3 = new JScrollPane[1000];
    JScrollPane[] scroll4 = new JScrollPane[1000];
    JScrollPane[] scroll5 = new JScrollPane[1000];
    JTextArea codesim = new JTextArea();
    JButton next, prev;
    JLabel asd;

    JTextField row,col, finalans;
    JLabel rowlabel,collabel;
    JButton enter, clear, random, calculate;

    int count = 0;
    int click = 0;
    int sum=0;


    public TSP(){
        super("Travelling Salesman Problem");

        random = new JButton(new AbstractAction("Random") {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowint = Integer.parseInt(row.getText());
                int colint = Integer.parseInt(col.getText());
                int min = 1;
                int max = 50;
                for(int i=0; i<rowint; i++) {
                    for (int j = 0; j < colint; j++) {
                        double rand = Math.random() * (max - min + 1) + min;
                        dtm1[0].setValueAt((int)rand,i,j);
                        if(i==j){
                            dtm1[0].setValueAt(-1,i,j);
                        }
                    }
                }
            }
        });

        enter = new JButton(new AbstractAction("Enter") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("GUMAGANA BATO");
                int rowint = Integer.parseInt(row.getText());
                int colint = Integer.parseInt(col.getText());
                if(rowint <= 0 && colint <=0){
                    JOptionPane.showMessageDialog(null,"Input a valid number larger than 0", "WARNING", JOptionPane.WARNING_MESSAGE);
                    row.setText("");
                    col.setText("");
                }
                else {
                    dtm1[0] = new DefaultTableModel(rowint, colint);
                    table1[0] = new JTable(dtm1[0]);
                    scroll1[0] = new JScrollPane(table1[0], JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                    label1[0] = new JTextField();
                    for (int i = 0; i < rowint; i++) {
                        for (int j = 0; j < colint; j++) {
                            if (i == j) {
                                dtm1[0].setValueAt(-1, i, j);
                            }
                        }
                    }



                    inside.add(label1[0]);
                    label1[0].setText("BASE VALUES");
                    inside.add(scroll1[0]);
                    mother = new JScrollPane(inside, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    mother.setPreferredSize(new Dimension(300, 300));
                    south.add(prev);
                    south.add(next);
                    scrollcodesim = new JScrollPane(codesimulation1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                    scrollcodesim.setPreferredSize(new Dimension(300, 300));
                    codesimulation1.add(codesim);
                    codesimulation.add(scrollcodesim, BorderLayout.CENTER);
                    codesimulation.add(south, BorderLayout.SOUTH);
                    container.add(center, BorderLayout.CENTER);
                    container.add(codesimulation, BorderLayout.SOUTH);
                    container.add(mother, BorderLayout.NORTH);
                    container.setBackground(Color.blue);
                    add(container);
                    container.setVisible(true);
                    mother.setVisible(true);
                    container.updateUI();
                    codesim.setEditable(false);
                }
            }
        });

        calculate = new JButton(new AbstractAction("Calculate") {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowint = Integer.parseInt(row.getText());
                int colint = Integer.parseInt(col.getText());

                int[][] values = new int[rowint][colint];
                int[][] OGGRAPH = new int[rowint][colint];
                String[][] placeholder = new String[rowint][colint];


                //initializer
                dtm5[0] = new DefaultTableModel(rowint,colint);
                table5[0] = new JTable(dtm5[0]);
                for(int i=0; i<rowint; i++){
                    for(int j=0; j<colint; j++){
                        System.out.println(rowint + " " + colint);
                        placeholder[i][j] = dtm1[0].getValueAt(i,j).toString();
                        values[i][j] = Integer.parseInt(placeholder[i][j]);
                        OGGRAPH[i][j] = Integer.parseInt(placeholder[i][j]);
                        dtm5[0].setValueAt(values[i][j],i,j);
                    }
                }


                int counter = rowint;
                while(counter != 0){

                    // ROW MINIMIZATION
                    dtm2[count] = new DefaultTableModel(rowint,colint);
                    table2[count] = new JTable(dtm2[count]);
                    scroll2[count] = new JScrollPane(table2[count], JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                    int[] result = new int[values.length];
                    for(int i=0; i< values.length; i++){
                        int minimum = Integer.MAX_VALUE; // HAHANAPIN DITO YUNG MINIMUM VALUE SA BAWAT ROW
                        for(int j=0; j<values[0].length; j++){
                            if(minimum > values[i][j] && values[i][j] != -1){
                                minimum=values[i][j];
                            }
                            result[i]=minimum;
                        }
                    }

                    // DITO YUNG PAGBABAWAS NG MGA VALUE
                    int[][] rowminimization = new int[rowint][colint];
                    for(int i=0; i<rowint;i++){
                        for(int j=0; j<colint; j++){
                            values[i][j] = (int)dtm5[count].getValueAt(i,j);
                            if(values[i][j] != -1){
                                rowminimization[i][j] = values[i][j]; // DITO BABAWASAN ANG BAWAT ROW GAMIT ANG MINIMUM VALUE NG BAWAT ROW
                                rowminimization[i][j] -= result[i];
                                dtm2[count].setValueAt(rowminimization[i][j],i,j);
                                values[i][j] = (int)dtm2[count].getValueAt(i,j);
                            }
                            else if(values[i][j] == -1){ // SINESET DITO ANG -1 KAPAG -1 SYA SA TUNAY NA VALUES
                                dtm2[count].setValueAt(-1,i,j);
                            }
                        }
                    }
                    // END NG ROW MINIMIZATION


                    // COL MINIMIZATION
                    dtm3[count] = new DefaultTableModel(rowint,colint);
                    table3[count] = new JTable(dtm3[count]);
                    scroll3[count] = new JScrollPane(table3[count], JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                    for(int i=0; i< values.length; i++){
                        int minimum = Integer.MAX_VALUE; // HAHANAPIN DITO YUNG MINIMUM VALUE SA BAWAT COLUMN
                        for(int j=0; j<values[0].length; j++){
                            if(minimum > values[j][i] && values[j][i] != -1){
                                minimum=values[j][i];
                            }
                            result[i]=minimum;
                        }
                    }

                    // DITO YUNG PAGBABAWAS NG MGA VALUE
                    int[][] columnminimization = new int[rowint][colint];
                    for(int i=0; i<rowint;i++){
                        for(int j=0; j<colint;j++){
                            values[j][i] = (int)dtm2[count].getValueAt(j,i);
                            if(values[j][i] != -1){
                                columnminimization[j][i] = values[j][i]; // DITO BABAWASAN ANG BAWAT COLUMN GAMIT ANG MINIMUM VALUE NG BAWAT COLUMN
                                columnminimization[j][i] -= result[i];
                                dtm3[count].setValueAt(columnminimization[j][i],j,i);
                                values[j][i] = (int)dtm3[count].getValueAt(j,i);

                            }
                            else if(values[j][i] == -1){ // SINESET DITO ANG -1 KAPAG -1 SYA SA TUNAY NA VALUES
                                dtm3[count].setValueAt(-1,j,i);
                            }

                        }
                    }
                    // END NG COL MINIMIZATION


                    // PENALTY
                    dtm4[count] = new DefaultTableModel(rowint,colint);
                    table4[count] = new JTable(dtm4[count]);
                    scroll4[count] = new JScrollPane(table4[count], JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                    int penres = 0, penres2 = 0;
                    int[][] penalize = new int[rowint][colint]; // DITO HINAHANAP ANG MINIMUM VALUE SA ROW AT COLUMN NA MAY 0
                    for(int i = 0; i < rowint; i++) {
                        for(int j = 0; j < colint; j++) {
                            if(values[i][j] == 0) {
                                int minimum = Integer.MAX_VALUE; // DITO KINUKUHA ANG MINIMUM VALUE GALING SA ROW
                                for(int x = 0; x < rowint; x++) {
                                    if (values[i][x] != -1) {
                                        if (x == j)
                                            continue;
                                        else if (minimum > values[i][x]) {
                                            penres = values[i][x];
                                            minimum = penres;
                                        }
                                    }
                                }
                                minimum = Integer.MAX_VALUE; // DITO KINUKUHA ANG MINIMUM VALUE GALING SA COLUMN
                                for(int x = 0; x < colint; x++) {
                                    if(values[x][j] != -1) {
                                        if (x == i)
                                            continue;
                                        else if(minimum > values[x][j]) {
                                            penres2 = values[x][j];
                                            minimum = penres2;
                                        }
                                    }
                                }
                                penalize[i][j] = penres + penres2; // IAADD ANG MINIMUM VALUE NG ROW AT COLUMN
                            }
                            else if(values[i][j] != 0){ // KAPAG ANG VALUE AY HINDI 0 GAGAWING PANSAMANTALANG -1 ETO SA TABLE NA ITO
                                penalize[i][j] = -1;
                            }
                            dtm4[count].setValueAt(penalize[i][j],i,j);
                        }
                    }



                    // DITO DAPAT HAHANAPIN YUNG MALAKING VALUE
                    int maximum=0;
                    int penaltyrow=0;
                    int penaltycol=0;
                    for(int i = 0; i < rowint; i++) {
                        for (int j = 0; j < colint; j++) {
                            if(maximum < penalize[i][j]){ // DITO INIISTORE ANG PINAKAMATAAS NA VALUE
                                maximum = penalize[i][j];
                                penaltyrow = i;
                                penaltycol = j;
                            }
                            else if(maximum == penalize[i][j]) { // KAPAG ANG PINAKAMATAAS NA VALUE AY MAY KAPAREHA, KUKUNIN NITO ANG MASMABABANG VALUE SA ORIGINAL VALUES
                                if(OGGRAPH[i][j] < OGGRAPH[penaltyrow][penaltycol]) {
                                    penaltyrow = i;
                                    penaltycol = j;
                                }
                            }
                        }
                    }

                    // KAPAG 0 ANG NATITIRANG VALUE
                    if (maximum == 0) {
                        for(int i = 0; i < rowint; i++) {
                            for (int j = 0; j < colint; j++) {
                                if(penalize[i][j] == 0){
                                    if(maximum <= OGGRAPH[i][j]){ // DITO IKUKUMPARA KUNG ALIN SA MGA ZERO ANG MASMABABA ANG VALUE
                                        maximum = OGGRAPH[i][j];
                                        penaltyrow = i;
                                        penaltycol = j;
                                    }
                                }
                            }
                        }
                    }

                    // BAGONG VALUES TABLE
                    dtm5[count+1] = new DefaultTableModel(rowint,colint);
                    table5[count+1] = new JTable(dtm5[count+1]);
                    scroll5[count+1] = new JScrollPane(table5[count+1], JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                    for(int i = 0; i < rowint; i++) {
                        for(int j = 0; j < colint; j++) {
                            if(i == penaltyrow || j == penaltycol) {
                                values[i][j] = -1;
                                dtm5[count+1].setValueAt(values[i][j],i,j);
                            }

                            dtm5[count+1].setValueAt(values[i][j],i,j);
                        }
                    }

                    for(int i=0; i<rowint; i++){
                        for(int j=0; j<colint; j++){
                            if(values[i][j] == values[j][i]){
                                values[i][j] = -1;
                                dtm5[count+1].setValueAt(-1, penaltycol, penaltyrow);
                                System.out.println(penaltyrow + "-" + penaltycol);
                            }
                        }
                    }

                    path[count] = new JTextField();
                    path[count].setText((penaltyrow) + "-" + (penaltycol) + "\n" + OGGRAPH[(penaltyrow)][(penaltycol)]);
                    path[count].setPreferredSize(new Dimension(100,30));
                    center.add(path[count]);
                    path[count].setEditable(false);

                    sum = sum + OGGRAPH[penaltyrow][penaltycol];
                    finalans.setText(String.valueOf(sum));
                    finalans.setPreferredSize(new Dimension(100,30));
                    finalans.setEditable(false);


                    label2[count] = new JTextField();
                    label2[count].setText("ROW MINIMIZATION");
                    label3[count] = new JTextField();
                    label3[count].setText("COLUMN MINIMIZATION");
                    label4[count] = new JTextField();
                    label4[count].setText("PENALTY");
                    label5[count+1] = new JTextField();
                    label5[count+1].setText("NEW BASE VALUE");

                    inside.add(label2[count]);
                    inside.add(scroll2[count]);
                    inside.add(label3[count]);
                    inside.add(scroll3[count]);
                    inside.add(label4[count]);
                    inside.add(scroll4[count]);
                    inside.add(label5[count+1]);
                    inside.add(scroll5[count+1]);

                    counter--;
                    count++;
                }

                center.add(finalans);
                container.add(center, BorderLayout.CENTER);
                mother.updateUI();
                container.updateUI();
            }
        });
        finalans = new JTextField();

        next = new JButton(new AbstractAction("Next") {
            @Override
            public void actionPerformed(ActionEvent e) {
                click++;
                Codes(click);
                System.out.println("Next: " + click);

            }
        });

        prev = new JButton(new AbstractAction("Prev") {
            @Override
            public void actionPerformed(ActionEvent e) {
                click--;
                Codes(click);
                System.out.println("Prev: " + click);
            }
        });

        clear = new JButton(new AbstractAction("Clear") {
            @Override
            public void actionPerformed(ActionEvent e) {
                row.setText("");
                col.setText("");
                codesimulation.removeAll();
                codesimulation.revalidate();
                codesimulation.repaint();
                center.removeAll();
                center.revalidate();
                center.repaint();
                mother.removeAll();
                mother.revalidate();
                mother.repaint();
                inside.removeAll();
                inside.revalidate();
                inside.repaint();
                container.removeAll();
                container.revalidate();
                container.repaint();
                count =0;
            }
        });

        upper = new JPanel(new GridLayout(3,2));
        container = new JPanel(new BorderLayout());
        inside = new JPanel();
        inside.setLayout(new BoxLayout(inside,BoxLayout.Y_AXIS));
        //inside.setPreferredSize(new Dimension(1280,50));

        codesimulation = new JPanel();
        codesimulation.setLayout(new BoxLayout(codesimulation,BoxLayout.Y_AXIS));
        codesimulation.setPreferredSize(new Dimension(300,250));
        codesimulation.setBackground(Color.lightGray);

        codesimulation1 = new JPanel(new GridLayout(1,1));

        center = new JPanel(new FlowLayout(FlowLayout.CENTER));
        center.setPreferredSize(new Dimension(500,200));
        center.setBackground(Color.lightGray);

        south = new JPanel(new FlowLayout(FlowLayout.CENTER));
        south.setPreferredSize(new Dimension(100,30));
        south.setBackground(Color.lightGray);

        upper1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        upper2 = new JPanel(new FlowLayout(FlowLayout.LEFT));

        rowlabel = new JLabel("Enter Row: ");
        collabel = new JLabel("Enter Column: ");
        row = new JTextField();
        col = new JTextField();

        upper1.add(enter);
        upper1.add(calculate);
        upper2.add(random);
        upper2.add(clear);


        upper.add(rowlabel);
        upper.add(collabel);
        upper.add(row);
        upper.add(col);
        upper.add(upper1);
        upper.add(upper2);


        add(upper, BorderLayout.NORTH);
        setSize(1280,750);
        setVisible(true);
    }
    public void Codes(int click){
        if(click == 1){
            codesim.setText("  dtm1[0] = new DefaultTableModel(rowint,colint);\n" +
                    "                table1[0] = new JTable(dtm1[0]);\n" +
                    "                scroll1[0] = new JScrollPane(table1[0], JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);\n" +
                    "                label1[0] = new JTextField();\n" +
                    "                for(int i=0; i<rowint; i++){\n" +
                    "                    for(int j=0; j<colint; j++){\n" +
                    "                        if(i==j){\n" +
                    "                            dtm1[0].setValueAt(-1,i,j);\n" +
                    "                        }\n" +
                    "                    }\n" +
                    "                }");
        }
        if(click == 2){
            codesim.setText(" dtm5[0] = new DefaultTableModel(rowint,colint);\n" +
                    "                table5[0] = new JTable(dtm5[0]);\n" +
                    "                for(int i=0; i<rowint; i++){\n" +
                    "                    for(int j=0; j<colint; j++){\n" +
                    "                        System.out.println(rowint + \" \" + colint);\n" +
                    "                        placeholder[i][j] = dtm1[0].getValueAt(i,j).toString();\n" +
                    "                        values[i][j] = Integer.parseInt(placeholder[i][j]);\n" +
                    "                        OGGRAPH[i][j] = Integer.parseInt(placeholder[i][j]);\n" +
                    "                        dtm5[0].setValueAt(values[i][j],i,j);\n" +
                    "                    }\n" +
                    "                }" +
                    "\n");
        }
        if(click == 3) {
            codesim.setText("  int counter = rowint;\n" +
                    "                while(counter != 0){\n" +
                    "                    dtm2[count] = new DefaultTableModel(rowint,colint);\n" +
                    "                    table2[count] = new JTable(dtm2[count]);\n" +
                    "                    scroll2[count] = new JScrollPane(table2[count], JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);\n" +
                    "                    int[] result = new int[values.length];\n" +
                    "                    for(int i=0; i< values.length; i++){\n" +
                    "                        int minimum = Integer.MAX_VALUE; \n" +
                    "                        for(int j=0; j<values[0].length; j++){\n" +
                    "                            if(minimum > values[i][j] && values[i][j] != -1){\n" +
                    "                                minimum=values[i][j];\n" +
                    "                            }\n" +
                    "                            result[i]=minimum;\n" +
                    "                        }\n" +
                    "                    }" +
                    " int[][] rowminimization = new int[rowint][colint];\n" +
                    "                    for(int i=0; i<rowint;i++){\n" +
                    "                       for(int j=0; j<colint; j++){\n" +
                    "                    values[i][j] = (int)dtm5[count].getValueAt(i,j);\n" +
                    "                    if(values[i][j] != -1){\n" +
                    "                    rowminimization[i][j] = values[i][j]; \n" +
                    "                    rowminimization[i][j] -= result[i];\n" +
                    "                    dtm2[count].setValueAt(rowminimization[i][j],i,j);\n" +
                    "                    values[i][j] = (int)dtm2[count].getValueAt(i,j);\n" +
                    "                    }\n" +
                    "                    else if(values[i][j] == -1){ \n" +
                    "                    dtm2[count].setValueAt(-1,i,j);\n" +
                    "                    }\n" +
                    "            }\n" +
                    "      }");
        }

        if(click == 4) {
            codesim.setText("  dtm3[count] = new DefaultTableModel(rowint,colint);\n" +
                    "                    table3[count] = new JTable(dtm3[count]);\n" +
                    "                    scroll3[count] = new JScrollPane(table3[count], JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);\n" +
                    "                    for(int i=0; i< values.length; i++){\n" +
                    "                        int minimum = Integer.MAX_VALUE; \n" +
                    "                        for(int j=0; j<values[0].length; j++){\n" +
                    "                            if(minimum > values[j][i] && values[j][i] != -1){\n" +
                    "                                minimum=values[j][i];\n" +
                    "                            }\n" +
                    "                            result[i]=minimum;\n" +
                    "                        }\n" +
                    "                    }\n"+
                    " int[][] columnminimization = new int[rowint][colint];\n" +
                            "                    for(int i=0; i<rowint;i++){\n" +
                            "                        for(int j=0; j<colint;j++){\n" +
                            "                            values[j][i] = (int)dtm2[count].getValueAt(j,i);\n" +
                            "                            if(values[j][i] != -1){\n" +
                            "                                columnminimization[j][i] = values[j][i];\n" +
                            "                                columnminimization[j][i] -= result[i];\n" +
                            "                                dtm3[count].setValueAt(columnminimization[j][i],j,i);\n" +
                            "                                values[j][i] = (int)dtm3[count].getValueAt(j,i);\n" +
                            "                            }\n" +
                            "                            else if(values[j][i] == -1){ // SINESET DITO ANG -1 KAPAG -1 SYA SA TUNAY NA VALUES\n" +
                            "                                dtm3[count].setValueAt(-1,j,i);\n" +
                            "                            }\n" +
                            "                        }\n" +
                            "                    }"

            );
        }

        if(click == 5) {
            codesim.setText(" dtm4[count] = new DefaultTableModel(rowint,colint);\n" +
                    "                    table4[count] = new JTable(dtm4[count]);\n" +
                    "                    scroll4[count] = new JScrollPane(table4[count], JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);\n" +
                    "                    int penres = 0, penres2 = 0;\n" +
                    "                    int[][] penalize = new int[rowint][colint]; \n" +
                    "                    for(int i = 0; i < rowint; i++) {\n" +
                    "                        for(int j = 0; j < colint; j++) {\n" +
                    "                            if(values[i][j] == 0) {\n" +
                    "                                int minimum = Integer.MAX_VALUE;\n" +
                    "                                for(int x = 0; x < rowint; x++) {\n" +
                    "                                    if (values[i][x] != -1) {\n" +
                    "                                        if (x == j)\n" +
                    "                                            continue;\n" +
                    "                                        else if (minimum > values[i][x]) {\n" +
                    "                                            penres = values[i][x];\n" +
                    "                                            minimum = penres;\n" +
                    "                                        }\n" +
                    "                                    }\n" +
                    "                                }\n" +
                            "   minimum = Integer.MAX_VALUE; \n" +
                            "                                for(int x = 0; x < colint; x++) {\n" +
                            "                                    if(values[x][j] != -1) {\n" +
                            "                                        if (x == i)\n" +
                            "                                            continue;\n" +
                            "                                        else if(minimum > values[x][j]) {\n" +
                            "                                            penres2 = values[x][j];\n" +
                            "                                            minimum = penres2;\n" +
                            "                                        }\n" +
                            "                                    }\n" +
                            "                                }\n" +
                            "                                penalize[i][j] = penres + penres2;\n" +
                            "                            }\n" +
                            "                            else if(values[i][j] != 0){ \n" +
                            "                                penalize[i][j] = -1;\n" +
                            "                            }\n" +
                            "                            dtm4[count].setValueAt(penalize[i][j],i,j);\n" +
                            "                        }\n" +
                            "                    }");
        }

        if(click == 6) {
            codesim.setText("  for(int i = 0; i < rowint; i++) {\n" +
                    "                        for (int j = 0; j < colint; j++) {\n" +
                    "                            if(maximum < penalize[i][j]){\n" +
                    "                                maximum = penalize[i][j];\n" +
                    "                                penaltyrow = i;\n" +
                    "                                penaltycol = j;\n" +
                    "                            }\n" +
                    "                            else if(maximum == penalize[i][j]) { \n" +
                    "                                if(OGGRAPH[i][j] < OGGRAPH[penaltyrow][penaltycol]) {\n" +
                    "                                    penaltyrow = i;\n" +
                    "                                    penaltycol = j;\n" +
                    "                                }\n" +
                    "                            }\n" +
                    "                        }\n" +
                    "                    }");
        }
        if(click == 7) {
            codesim.setText("if (maximum == 0) {\n" +
                    "                        for(int i = 0; i < rowint; i++) {\n" +
                    "                            for (int j = 0; j < colint; j++) {\n" +
                    "                                if(penalize[i][j] == 0){\n" +
                    "                                    if(maximum <= OGGRAPH[i][j]){ // DITO IKUKUMPARA KUNG ALIN SA MGA ZERO ANG MASMABABA ANG VALUE\n" +
                    "                                        maximum = OGGRAPH[i][j];\n" +
                    "                                        penaltyrow = i;\n" +
                    "                                        penaltycol = j;\n" +
                    "                                    }\n" +
                    "                                }\n" +
                    "                            }\n" +
                    "                        }\n" +
                    "                    }");
        }
        if(click == 8) {
            codesim.setText("dtm5[count+1] = new DefaultTableModel(rowint,colint);\n" +
                    "                    table5[count+1] = new JTable(dtm5[count+1]);\n" +
                    "                    scroll5[count+1] = new JScrollPane(table5[count+1], JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);\n" +
                    "                    for(int i = 0; i < rowint; i++) {\n" +
                    "                        for(int j = 0; j < colint; j++) {\n" +
                    "                            if(i == penaltyrow || j == penaltycol) {\n" +
                    "                                values[i][j] = -1;\n" +
                    "                                dtm5[count+1].setValueAt(values[i][j],i,j);\n" +
                    "                            }\n" +
                    "\n" +
                    "                            dtm5[count+1].setValueAt(values[i][j],i,j);\n" +
                    "                        }\n" +
                    "                    }\n" +
                    "\n" +
                    "                    for(int i=0; i<rowint; i++){\n" +
                    "                        for(int j=0; j<colint; j++){\n" +
                    "                            if(values[i][j] == values[j][i]){\n" +
                    "                                values[i][j] = -1;\n" +
                    "                                dtm5[count+1].setValueAt(-1, penaltycol, penaltyrow);\n" +
                    "                                System.out.println(penaltyrow + \"-\" + penaltycol);\n" +
                    "                            }\n" +
                    "                        }\n" +
                    "                    }");
        }
        if(click == 9) {
            codesim.setText(" path[count] = new JTextField();\n" +
                    "                    path[count].setText((penaltyrow) + \"-\" + (penaltycol) + \"\\n\" + OGGRAPH[(penaltyrow)][(penaltycol)]);\n" +
                    "                    path[count].setPreferredSize(new Dimension(100,30));\n" +
                    "                    center.add(path[count]);");

        }
    }

}