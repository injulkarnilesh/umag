package in.solve.problems.companies.customer.billing;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CampfireGame {

    static class NumberedStudent {
        private int number;
        private String color;

        public NumberedStudent(int number, String color) {
            this.number = number;
            this.color = color;
        }

        public int getNumber() {
            return number;
        }

        public String getColor() {
            return color;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        List<String> inputByLine = new ArrayList<String>();
        try {
            // Get the object of DataInputStream
            InputStreamReader isr = new InputStreamReader(System.in,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line = "";
            while ((line = br.readLine()) != null){
                inputByLine.add(line.toString());
            }

            List<NumberedStudent> circle = new ArrayList<NumberedStudent>();
            Integer noOfStudents = Integer.parseInt(inputByLine.get(0));
            String intialSetUp = inputByLine.get(1);
            String[] setup = intialSetUp.split(" ");
            int initialNoOfStudents = setup.length/2;

            for (int i = 0; i < initialNoOfStudents; i++) {
                int number = Integer.parseInt(setup[i*2]);
                String color = setup[i*2 + 1];
                final NumberedStudent numberedStudent = new NumberedStudent(number, color);
                circle.add(numberedStudent);
            }
            int noOfNewStudents = noOfStudents-initialNoOfStudents;
            for (int i=2; i< noOfNewStudents; i++) {
                String[] newStudentDetail = inputByLine.get(i).split(" ");
                NumberedStudent s = new NumberedStudent(Integer.parseInt(newStudentDetail[0]), newStudentDetail[1]);
                circle.add(s.getNumber() - 1, s);
            }

            for (NumberedStudent s: circle) {
                System.out.println(s.getNumber() + ": "+ s.getColor());
            }

            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
            for (int i = 0; i < circle.size(); i++) {
                int next = nextIndex(i, circle.size());
                int currentCount = 1;
                while(next != i && circle.get(i).getColor().equals(circle.get(next).getColor())) {
                    currentCount++;
                    next = nextIndex(next, circle.size());
                }
                map.put(i, currentCount);
            }

            for (int i = 0; i < circle.size(); i++) {
                final Integer continuesCount = map.get(i);
                System.out.println( i + " -> " + continuesCount);
            }

            isr.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    static int nextIndex(int current, int size) {
        if (current < (size-1))
            return current+1;
        return 0;
    }
}
