package Exercitiul1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class mainApp {

    static class Punct {
        private final int x;
        private final int y;

        public Punct(int x, int y, int z) {
            super();
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    static class Parabola {
        private final int a, b, c;

        public Parabola(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public Punct varfParabola() {
            int x = -b / (2 * a);
            int y = ((-b * b) + 4 * a * c) / (4 * a);
            return new Punct(x, y, 0);
        }

        @Override
        public String toString() {
            return "f(x)=" + a + "x^2+" + b + "x+" + c;
        }

        public static Punct mijSegVarf(Parabola a, Parabola b) {
            Punct varfA = a.varfParabola();
            Punct varfB = b.varfParabola();
            int x = (varfA.getX() + varfB.getX()) / 2;
            int y = (varfA.getY() + varfB.getY()) / 2;
            return new Punct(x, y, 0);
        }

        public static double lungimeSegment(Parabola a, Parabola b) {
            Punct varfA = a.varfParabola();
            Punct varfB = b.varfParabola();
            return Math.hypot(varfA.getX() - varfB.getX(), varfA.getY() - varfB.getY());
        }
    }

    public static void main(String[] args) {
        List<Parabola> parabole = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("src/Exercitiul1/in.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] coefficients = line.split(" ");
                int a = Integer.parseInt(coefficients[0]);
                int b = Integer.parseInt(coefficients[1]);
                int c = Integer.parseInt(coefficients[2]);
                Parabola p = new Parabola(a, b, c);
                parabole.add(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Parabola p : parabole) {
            System.out.println(p);
            System.out.println("Varful parabolei: " + p.varfParabola());
        }

        if (parabole.size() >= 2) {
            Parabola a = parabole.get(0);
            Parabola b = parabole.get(1);
            System.out.println("Mijlocul segmentului: " + Parabola.mijSegVarf(a, b));
            System.out.println("Lungimea segmentului: " + Parabola.lungimeSegment(a, b));
        }
    }
}

