package converter;
import javax.swing.*;
import java.awt.*;

/**
 * A converter programme that works with multiple number systems and convert it
 * GUI interface realized by swing and awt package
 * @author Nikita Filimonov
 */

public class Converter extends JFrame {
    private static final long serialVersionUID = 1L;
    
    /**
     * Checking the letters in the num for correctness
     * @param str taken users input num as a string
     * @return 'true' if letters of string is allow and 'false' if it not allowed
     */
    public boolean isAllowed(String str) {

        boolean isAllow = false;

        for (int i = 0; i < str.length(); i++) {

            char ch = str.charAt(i);

            isAllow = ch >= 'a' && ch <= 'f'
                    ||
                    ch >= 'A' && ch <= 'F'
                    ||
                    ch >= '0' && ch <= '9';
        }
        return isAllow;
    }


    /**
     * Checking string for letter presence
     * @param str taken users input num as a string
     * @return 'true' if num have letters and 'false' if there are no letters
     */
    public boolean hasLetters(String str) {

        boolean hasLet = false;

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z') {
                hasLet = true;
                break;
            }
        }
        return hasLet;
    }

    public Converter() {
        super("Converter");

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        /* Output the welcome window */
        JOptionPane.showMessageDialog(this, "Welcome!\nThis program converts a number from a given number system to another system.");

        /* Fill out our layout */
        JLabel label = new JLabel("Convert your num");
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weighty = 3;

        add(label, gbc);

        /* Box struct within 2 group of radiobutton */
        Box boxIn = new Box(BoxLayout.Y_AXIS);
        JRadioButton decimal = new JRadioButton("decimal");
        decimal.setSelected(true);
        JRadioButton binary = new JRadioButton("binary");
        JRadioButton octal = new JRadioButton("octal");
        JRadioButton hexal = new JRadioButton("hexal");

        ButtonGroup group = new ButtonGroup();
        group.add(decimal);
        group.add(binary);
        group.add(octal);
        group.add(hexal);



        boxIn.add(decimal);
        boxIn.add(binary);
        boxIn.add(octal);
        boxIn.add(hexal);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(boxIn, gbc);

        Box boxTo = new Box(BoxLayout.Y_AXIS);
        JRadioButton decimalTo = new JRadioButton("decimal");
        decimalTo.setSelected(true);
        JRadioButton binaryTo = new JRadioButton("binary");
        JRadioButton octalTo = new JRadioButton("octal");
        JRadioButton hexalTo = new JRadioButton("hexal");

        ButtonGroup groupTo = new ButtonGroup();
        groupTo.add(decimalTo);
        groupTo.add(binaryTo);
        groupTo.add(octalTo);
        groupTo.add(hexalTo);

        boxTo.add(decimalTo);
        boxTo.add(binaryTo);
        boxTo.add(octalTo);
        boxTo.add(hexalTo);

        gbc.gridx = 2;
        gbc.gridy = 1;
        add(boxTo, gbc);



        /* Add 2 text field for input num and output converting num */
        JTextField tf1 = new JTextField(30);
        tf1.setFont(new Font("Arial", Font.PLAIN, 14));
        tf1.setMinimumSize(new Dimension(230, 30));
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(tf1, gbc);

        JButton button = new JButton("Convert");
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(button, gbc);

        JTextField tf2 = new JTextField(30);
        tf2.setFont(new Font("Arial", Font.PLAIN, 14));
        tf2.setMinimumSize(new Dimension(230, 30));
        gbc.gridx = 2;
        gbc.gridy = 3;
        add(tf2, gbc);


        /* Create a listener for 'covert' button and run convert process */
        button.addActionListener(e -> {

            /* Checking for any symbols in the field */
            if (tf1.getText().length() == 0) return;

            boolean isNegative = false;

            /* Num validation check */
            if (hasLetters(tf1.getText()) && hexal.isSelected()) {
                if (isAllowed(tf1.getText())) {

                    String numStr = tf1.getText();
                    int numOut = Integer.parseInt(numStr, 16);

                    if (decimalTo.isSelected()) tf2.setText(Integer.toString(numOut));
                    if (binaryTo.isSelected()) tf2.setText(Integer.toBinaryString(numOut));
                    if (octalTo.isSelected()) tf2.setText(numStr);
                    if (hexalTo.isSelected()) tf2.setText(Integer.toHexString(numOut));

                }
                else
                    JOptionPane.showMessageDialog(null, "A hexadecimal number can only contain digits '0' - '9' and letters A,B,C,D,E,F without case sensitive.", "Warning", JOptionPane.WARNING_MESSAGE);
                return ;
            }

            if (binary.isSelected()) {

                String numStr = tf1.getText();

                for (int i = 0; i < numStr.length(); i++) {
                    if (numStr.charAt(i) >= '2') {
                        JOptionPane.showMessageDialog(null, "A binary number can only contain digits '0' or '1'.", "Warning", JOptionPane.WARNING_MESSAGE);
                        return ;
                    }
                }

                /* Type overload checking */
                if (numStr.length() >= 32) {
                    JOptionPane.showMessageDialog(null, "Sorry(\nYour num is too large.", "Warning", JOptionPane.WARNING_MESSAGE);
                    return ;
                }
                else {

                    if (numStr.charAt(0) == '-') {
                        isNegative = true;
                        numStr = numStr.substring(1);
                    }

                    int numOut = Integer.parseInt(numStr, 2);

                    if (decimalTo.isSelected()) {
                        if (isNegative)
                            tf2.setText('-' + Integer.toString(numOut));
                        else
                            tf2.setText(Integer.toString(numOut));
                    }
                    if (binaryTo.isSelected()) tf2.setText(tf1.getText());
                    if (octalTo.isSelected()) {
                        if (isNegative)
                            tf2.setText('-' + Integer.toOctalString(numOut));
                        else
                            tf2.setText(Integer.toOctalString(numOut));
                    }
                    if (hexalTo.isSelected()) {
                        if (isNegative)
                            tf2.setText('-' + Integer.toHexString(numOut));
                        else
                            tf2.setText(Integer.toHexString(numOut));
                    }
                }
            }

            /*
            Type overload checking
             */
            double numD = Double.parseDouble(tf1.getText());

            if (numD > Integer.MAX_VALUE) {
                JOptionPane.showMessageDialog(null, "Sorry(\nYour num is too large.", "Warning", JOptionPane.WARNING_MESSAGE);
                return ;
            }
            else if (numD < Integer.MIN_VALUE) {
                JOptionPane.showMessageDialog(null, "Sorry(\nYour num is too small.", "Warning", JOptionPane.WARNING_MESSAGE);
                return ;
            }
            int num = (int)numD;

            if (decimal.isSelected()) {
                if (num < 0) {
                    isNegative = true;
                    num = Math.abs(num);
                }
                if (decimalTo.isSelected()) tf2.setText(tf1.getText());
                if (binaryTo.isSelected()) {
                    if (isNegative)
                        tf2.setText('-' + Integer.toBinaryString(num));
                    else
                        tf2.setText(Integer.toBinaryString(num));
                }
                if (octalTo.isSelected()) {
                    if (isNegative)
                        tf2.setText('-' + Integer.toOctalString(num));
                    else
                        tf2.setText(Integer.toOctalString(num));
                }
                if (hexalTo.isSelected()) {
                    if (isNegative)
                        tf2.setText('-' + Integer.toHexString(num));
                    else
                        tf2.setText(Integer.toHexString(num));
                }
            }

            if (octal.isSelected()) {

                String numStr = Integer.toString(num);

                for (int i = 0; i < numStr.length(); i++) {
                    if (numStr.charAt(i) >= '8') {
                        JOptionPane.showMessageDialog(null, "An octal number can only contain numbers '0' - '7'.", "Warning", JOptionPane.WARNING_MESSAGE);
                        return ;
                    }
                }

                if (numStr.charAt(0) == '-') {
                    isNegative = true;
                    numStr = numStr.substring(1);
                }

                int numOut = Integer.parseInt(numStr, 8);

                if (decimalTo.isSelected()) {
                    if (isNegative)
                        tf2.setText('-' + Integer.toString(numOut));
                    else
                        tf2.setText(Integer.toString(numOut));
                }
                if (binaryTo.isSelected()) {
                    if (isNegative)
                        tf2.setText('-' + Integer.toBinaryString(numOut));
                    else
                        tf2.setText(Integer.toBinaryString(numOut));
                }
                if (octalTo.isSelected()) tf2.setText(tf1.getText());
                if (hexalTo.isSelected()) {
                    if (isNegative)
                        tf2.setText('-' + Integer.toHexString(numOut));
                    else
                        tf2.setText(Integer.toHexString(numOut));
                }
            }


        });

        /* Window settings */
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(640, 280));
        setTitle("Converter");
        setVisible(true);
    }


    public static void main(String[] args) {
        new Converter();
    }
}
