import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StringCalculator {
    static JTextField displayArea = new JTextField(); 
    //Initializing all the variables 
    static String operandA="";     
    static String operandB="";
    static String operator="";
    static boolean isOperatorEntered = false;
    static int result = 0;

  //method to display selected numerical key and store value it in Operand variables
    public static void numericListener(int i) {
        displayArea.setText(displayArea.getText() + i); //display all the selected key
        if (isOperatorEntered)              //If operator is not selected
            operandB = operandB + i;        //then assign to second operand
        else                                //else 
            operandA = operandA + i;        //assign to second operation
        calculatorStream();                 //to display variable value status in console
    }
   //method to display selected expression key and store it in operator variable
    public static void operatorListener(String c) {
        if (!isOperatorEntered) {                  //If operator is not entered 
            isOperatorEntered = true;              //then set operator status to true
            operator = c;                           //store character to the operator variable
            displayArea.setText(displayArea.getText() + c);    //display all the selected text
            calculatorStream();
        }
    }
 // method to create to  create and add button to the panel and including action listener
    public static void addButtonSelf(String buttonName, ActionListener action, JPanel panel) {
        JButton button = new JButton(buttonName);
        button.addActionListener(action);
        panel.add(button);
    }
 //method to print current value of  variables in console
    public static void calculatorStream() {
        System.out.printf("\nOperandA = %s | OperandB = %s | Operator = %s | result =%d", operandA, operandB, operator, result);
    }
    //main function
    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");   //creating a frame
        frame.setVisible(true);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(displayArea, BorderLayout.NORTH);      //placing text field in north side using Border Layout
        displayArea.setPreferredSize(new Dimension(0, 40));  //setting height of the text field
        displayArea.setHorizontalAlignment(displayArea.RIGHT);//setting for text displaying in text field from right side
        JPanel bpanel = new JPanel(new GridLayout(4, 4));      //creating a panel and  adding 4x4 grid layout to the panel
        frame.add(bpanel);                                    //adding created panel to the frame
        
        
        ActionListener action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();        //variable to the store the selected value of selected key
                switch (command) {
                    case "C":                                //run statement if user selected clear(C) key
                        isOperatorEntered = false;           //and empty all the variables
                        operandA = "";
                        operandB = "";
                        operator = "";
                        result = 0;
                        displayArea.setText(null);
                        calculatorStream();
                        break;
                    case "=":                          
                        if (!isOperatorEntered) return;    //if operator is not selected return
                        switch (operator) {
                            case "+":
                                result = Integer.valueOf(operandA) + Integer.valueOf(operandB);
                                break;
                            case "-":
                                result = Integer.valueOf(operandA) - Integer.valueOf(operandB);
                                break;
                            case "*":
                                result = Integer.valueOf(operandA) * Integer.valueOf(operandB);
                                break;
                            case "/":
                                result = Integer.valueOf(operandA) / Integer.valueOf(operandB);
                                break;
                        }
                        displayArea.setText(String.valueOf(result));    //overwriting and displaying result
                        isOperatorEntered = false;                      //setting operator status false
                        operandA = String.valueOf(result);             //assigning result to value as string in first operand
                        operandB = "";                                 //assigning operator value as empty
                        calculatorStream();
                        break;
                       
                    default:                                       //Run this block if key are selected instead of clear(C) and equals(=)
                        if (command.matches("[0-9]")) {
                            numericListener(Integer.parseInt(command));
                        } else if (command.matches("[-+*/]")) {
                            operatorListener(command);
                        }
                        break;
                }
            }
        };
       //Setting up buttons layout and action listener
        for (int j = 9; j >= 1; j--) {
        	if(j==6)
        		addButtonSelf("C", action, bpanel);
        	if(j==3)
        		addButtonSelf("+", action, bpanel);
        	
            addButtonSelf(Integer.valueOf(j).toString(), action, bpanel);
            if(j==1)
            	addButtonSelf("-", action, bpanel);
               
        }
        addButtonSelf("/", action, bpanel);
        addButtonSelf("0", action, bpanel);
        addButtonSelf("*", action, bpanel);
        addButtonSelf("=", action, bpanel); 
        
        //above button setup is to  create a layout as      9 8 7 C
        //                                                  6 5 4 +
        //                                                  3 2 1 -
        //                                                  / 0 * =
        
    }
}
