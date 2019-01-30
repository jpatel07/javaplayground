package com.company;

public class CalculateHelper {
    private static final char ADD_SYMBOL = '+';
    private static final char SUBTRACT_SYMBOL = '-';
    private static final char MULTIPLY_SYMBOL = '*';
    private static final char DIVIDE_SYMBOL = '/';

    MathCommand command;
    double leftValue;
    double rightValue;
    double result;

    public void process(String statement) throws InvalidStatementException {
        //add 1.0 2.0
        String[] parts = statement.split(" ");
        if(parts.length != 3) {
            throw new InvalidStatementException("Incorrect number of filelds", statement);
        }
        String commandString = parts[0];

        try {
            leftValue = Double.parseDouble((parts[1]));
            rightValue = Double.parseDouble(parts[2]);
        } catch (NumberFormatException ex) {
            throw   new InvalidStatementException("Non-numerica data", statement, ex);
        }


        setCommandFromString(commandString);

        if(command == null)
            throw new InvalidStatementException("Invalid Command", statement);

        CalculateBase calculator = null;

        switch (command) {
            case Add:
                calculator = new Adder(leftValue, rightValue);
                break;
            case Subtract:
                calculator = new Subtracter(leftValue, rightValue);
                break;
            case Multiply:
                calculator = new Multiplier(leftValue, rightValue);
                break;
            case Divide:
                calculator = new Diveder(leftValue, rightValue);
                break;
        }
        calculator.calculate();
        result = calculator.getResult();
    }

    private void setCommandFromString(String commandString) {
        //add -> MathCommand.Add
        if (commandString.equalsIgnoreCase(MathCommand.Add.toString()))
            command = MathCommand.Add;
        else if (commandString.equalsIgnoreCase(MathCommand.Subtract.toString()))
            command = MathCommand.Subtract;
        else if (commandString.equalsIgnoreCase((MathCommand.Divide.toString())))
            command = MathCommand.Divide;
        else if (commandString.equalsIgnoreCase(MathCommand.Multiply.toString()))
            command = MathCommand.Multiply;
    }

    @Override
    public String toString() {
        char symbol = ' ';
        switch (command) {
            case Add:
                symbol = ADD_SYMBOL;
                break;
            case Subtract:
                symbol = SUBTRACT_SYMBOL;
                break;
            case Divide:
                symbol = DIVIDE_SYMBOL;
                break;
            case Multiply:
                symbol = MULTIPLY_SYMBOL;
                break;
        }

        StringBuffer sb = new StringBuffer(20);
        sb.append(leftValue);
        sb.append(' ');
        sb.append(symbol);
        sb.append(' ');
        sb.append(rightValue);
        sb.append(" = ");
        sb.append(result);
        return sb.toString();

    }

}
