import java.awt.event.*;
import java.util.*;
import java.io.*;

public class databaseFunctions implements ActionListener {
    //Found a more efficient and user friendly method of sorting
    // I found this and learned how to use it from this website
    // https://javaconceptoftheday.com/how-to-sort-a-text-file-in-java/
    class packageSort {
        String packageIDSort;
        String databaseIDSort;
        String packageNameSort;
        String dateArrivedSort;
        double weightSort;

        public packageSort(String packageIDSort, String databaseIDSort, String packageNameSort, String dateArrivedSort, double weightSort) {
            this.packageIDSort = packageIDSort;
            this.databaseIDSort = databaseIDSort;
            this.packageNameSort = packageNameSort;
            this.dateArrivedSort = dateArrivedSort;
            this.weightSort = weightSort;
        }
    }
    class packageIDCompare implements Comparator<packageSort> {
        public int compare(packageSort a1, packageSort a2) {
            return a1.packageIDSort.compareTo(a2.packageIDSort);
        }
    }
    class databaseIDCompare implements Comparator<packageSort> {
        public int compare(packageSort a1, packageSort a2) {
            return a1.databaseIDSort.compareTo(a2.databaseIDSort);
        }
    }
    class packageNameCompare implements Comparator<packageSort> {
        public int compare(packageSort a1, packageSort a2) {
            return a1.packageNameSort.compareTo(a2.packageNameSort);
        }
    }
    class dateArrivedCompare implements Comparator<packageSort> {
        public int compare(packageSort a1, packageSort a2) {
            return a1.dateArrivedSort.compareTo(a2.dateArrivedSort);
        }
    }
    class weightCompare implements Comparator<packageSort> {
        public int compare(packageSort a1, packageSort a2) {
            return Double.compare(a1.weightSort, a2.weightSort);
        }
    }
    public void actionPerformed(ActionEvent a) {
        if (a.getSource() == databaseBuild.btnPackageID) {
            File tempSort = new File("src\\tempSort.txt");
            try {
                BufferedReader br = new BufferedReader(new FileReader(applicationFunctions.dataFile));
                ArrayList<packageSort> packageIDList = new ArrayList<packageSort>();
                String currentLine = br.readLine();
                while (currentLine != null) {
                    String[] currentLineComponents = currentLine.split("<>");
                    String packageIDSort = currentLineComponents[0];
                    String databaseIDSort = currentLineComponents[1];
                    String packageNameSort = currentLineComponents[2];
                    String dateArrivedSort = currentLineComponents[3];
                    double weightSort = Double.valueOf(currentLineComponents[4]);
                    packageIDList.add(new packageSort(packageIDSort, databaseIDSort, packageNameSort, dateArrivedSort, weightSort));
                    currentLine = br.readLine();
                }
                Collections.sort(packageIDList, new packageIDCompare());
                tempSort.delete();
                tempSort = new File("src\\tempSort.txt");
                BufferedWriter bw = new BufferedWriter(new FileWriter(tempSort));
                for (packageSort packageSort : packageIDList) {
                    bw.write(packageSort.packageIDSort + "<>" + packageSort.databaseIDSort + "<>" + packageSort.packageNameSort + "<>" + packageSort.dateArrivedSort + "<>" + packageSort.weightSort + "<>");
                    bw.newLine();
                }
                br.close();
                bw.close();
                databaseBuild.frame.dispose();
                new databaseBuild();
            } catch (IOException error) {
                System.out.println("Error sorting package ID.");
            }
            try {
                applicationFunctions. dataFile.delete();
                applicationFunctions.dataFile = new File("src\\data.txt");
                BufferedReader br = new BufferedReader(new FileReader(tempSort));
                BufferedWriter bw = new BufferedWriter(new FileWriter(applicationFunctions.dataFile));
                String currentLine = br.readLine();
                while (currentLine != null) {
                    bw.write(currentLine);
                    bw.newLine();
                    currentLine = br.readLine();
                }
                br.close();
                bw.close();
                databaseBuild.frame.dispose();
                new databaseBuild();
            } catch (IOException error) {
                System.out.println("Error rewriting the file.");
            }
        }

        if (a.getSource() == databaseBuild.btnDatabaseID) {
            File tempSort = new File("src\\tempSort.txt");
            try {
                BufferedReader br = new BufferedReader(new FileReader(applicationFunctions.dataFile));
                ArrayList<packageSort> packageIDList = new ArrayList<packageSort>();
                String currentLine = br.readLine();
                while (currentLine != null) {
                    String[] currentLineComponents = currentLine.split("<>");
                    String packageIDSort = currentLineComponents[0];
                    String databaseIDSort = currentLineComponents[1];
                    String packageNameSort = currentLineComponents[2];
                    String dateArrivedSort = currentLineComponents[3];
                    double weightSort = Double.valueOf(currentLineComponents[4]);
                    packageIDList.add(new packageSort(packageIDSort, databaseIDSort, packageNameSort, dateArrivedSort, weightSort));
                    currentLine = br.readLine();
                }
                Collections.sort(packageIDList, new databaseIDCompare());
                tempSort.delete();
                tempSort = new File("src\\tempSort.txt");
                BufferedWriter bw = new BufferedWriter(new FileWriter(tempSort));
                for (packageSort packageSort : packageIDList) {
                    bw.write(packageSort.packageIDSort + "<>" + packageSort.databaseIDSort + "<>" + packageSort.packageNameSort + "<>" + packageSort.dateArrivedSort + "<>" + packageSort.weightSort + "<>");
                    bw.newLine();
                }
                br.close();
                bw.close();
            } catch (IOException error) {
                System.out.println("Error sorting package ID.");
            }
            try {
                applicationFunctions.dataFile.delete();
                applicationFunctions. dataFile = new File("src\\data.txt");
                BufferedReader br = new BufferedReader(new FileReader(tempSort));
                BufferedWriter bw = new BufferedWriter(new FileWriter(applicationFunctions.dataFile));
                String currentLine = br.readLine();
                while (currentLine != null) {
                    bw.write(currentLine);
                    bw.newLine();
                    currentLine = br.readLine();
                }
                br.close();
                bw.close();
                databaseBuild.frame.dispose();
                new databaseBuild();
            } catch (IOException error) {
                System.out.println("Error rewriting the file.");
            }
        }
        if (a.getSource() == databaseBuild.btnPackageName) {
            File tempSort = new File("src\\tempSort.txt");
            try {
                BufferedReader br = new BufferedReader(new FileReader(applicationFunctions.dataFile));
                ArrayList<packageSort> packageIDList = new ArrayList<packageSort>();
                String currentLine = br.readLine();
                while (currentLine != null) {
                    String[] currentLineComponents = currentLine.split("<>");
                    String packageIDSort = currentLineComponents[0];
                    String databaseIDSort = currentLineComponents[1];
                    String packageNameSort = currentLineComponents[2];
                    String dateArrivedSort = currentLineComponents[3];
                    double weightSort = Double.valueOf(currentLineComponents[4]);
                    packageIDList.add(new packageSort(packageIDSort, databaseIDSort, packageNameSort, dateArrivedSort, weightSort));
                    currentLine = br.readLine();
                }
                Collections.sort(packageIDList, new packageNameCompare());
                tempSort.delete();
                tempSort = new File("src\\tempSort.txt");
                BufferedWriter bw = new BufferedWriter(new FileWriter(tempSort));
                for (packageSort packageSort : packageIDList) {
                    bw.write(packageSort.packageIDSort + "<>" + packageSort.databaseIDSort + "<>" + packageSort.packageNameSort + "<>" + packageSort.dateArrivedSort + "<>" + packageSort.weightSort + "<>");
                    bw.newLine();
                }
                br.close();
                bw.close();
            } catch (IOException error) {
                System.out.println("Error sorting package ID.");
            }
            try {
                applicationFunctions. dataFile.delete();
                applicationFunctions.dataFile = new File("src\\data.txt");
                BufferedReader br = new BufferedReader(new FileReader(tempSort));
                BufferedWriter bw = new BufferedWriter(new FileWriter(applicationFunctions.dataFile));
                String currentLine = br.readLine();
                while (currentLine != null) {
                    bw.write(currentLine);
                    bw.newLine();
                    currentLine = br.readLine();
                }
                br.close();
                bw.close();
                databaseBuild.frame.dispose();
                new databaseBuild();
            } catch (IOException error) {
                System.out.println("Error rewriting the file.");
            }
        }
        if (a.getSource() == databaseBuild.btnDateArrived) {
            File tempSort = new File("src\\tempSort.txt");
            try {
                BufferedReader br = new BufferedReader(new FileReader(applicationFunctions.dataFile));
                ArrayList<packageSort> packageIDList = new ArrayList<packageSort>();
                String currentLine = br.readLine();
                while (currentLine != null) {
                    String[] currentLineComponents = currentLine.split("<>");
                    String packageIDSort = currentLineComponents[0];
                    String databaseIDSort = currentLineComponents[1];
                    String packageNameSort = currentLineComponents[2];
                    String dateArrivedSort = currentLineComponents[3];
                    double weightSort = Double.valueOf(currentLineComponents[4]);
                    packageIDList.add(new packageSort(packageIDSort, databaseIDSort, packageNameSort, dateArrivedSort, weightSort));
                    currentLine = br.readLine();
                }
                Collections.sort(packageIDList, new dateArrivedCompare());
                tempSort.delete();
                tempSort = new File("src\\tempSort.txt");
                BufferedWriter bw = new BufferedWriter(new FileWriter(tempSort));
                for (packageSort packageSort : packageIDList) {
                    bw.write(packageSort.packageIDSort + "<>" + packageSort.databaseIDSort + "<>" + packageSort.packageNameSort + "<>" + packageSort.dateArrivedSort + "<>" + packageSort.weightSort + "<>");
                    bw.newLine();
                }
                br.close();
                bw.close();
            } catch (IOException error) {
                System.out.println("Error sorting package ID.");
            }
            try {
                applicationFunctions.dataFile.delete();
                applicationFunctions.dataFile = new File("src\\data.txt");
                BufferedReader br = new BufferedReader(new FileReader(tempSort));
                BufferedWriter bw = new BufferedWriter(new FileWriter(applicationFunctions.dataFile));
                String currentLine = br.readLine();
                while (currentLine != null) {
                    bw.write(currentLine);
                    bw.newLine();
                    currentLine = br.readLine();
                }
                br.close();
                bw.close();
                databaseBuild.frame.dispose();
                new databaseBuild();
            } catch (IOException error) {
                System.out.println("Error rewriting the file.");
            }
        }
        if (a.getSource() == databaseBuild.btnWeight) {
            File tempSort = new File("src\\tempSort.txt");
            try {
                BufferedReader br = new BufferedReader(new FileReader(applicationFunctions.dataFile));
                ArrayList<packageSort> packageIDList = new ArrayList<packageSort>();
                String currentLine = br.readLine();
                while (currentLine != null) {
                    String[] currentLineComponents = currentLine.split("<>");
                    String packageIDSort = currentLineComponents[0];
                    String databaseIDSort = currentLineComponents[1];
                    String packageNameSort = currentLineComponents[2];
                    String dateArrivedSort = currentLineComponents[3];
                    double weightSort = Double.valueOf(currentLineComponents[4]);
                    packageIDList.add(new packageSort(packageIDSort, databaseIDSort, packageNameSort, dateArrivedSort, weightSort));
                    currentLine = br.readLine();
                }
                Collections.sort(packageIDList, new weightCompare());
                tempSort.delete();
                tempSort = new File("src\\tempSort.txt");
                BufferedWriter bw = new BufferedWriter(new FileWriter(tempSort));
                for (packageSort packageSort : packageIDList) {
                    bw.write(packageSort.packageIDSort + "<>" + packageSort.databaseIDSort + "<>" + packageSort.packageNameSort + "<>" + packageSort.dateArrivedSort + "<>" + packageSort.weightSort + "<>");
                    bw.newLine();
                }
                br.close();
                bw.close();
            } catch (IOException error) {
                System.out.println("Error sorting package ID.");
            }
            try {
                applicationFunctions. dataFile.delete();
                applicationFunctions.  dataFile = new File("src\\data.txt");
                BufferedReader br = new BufferedReader(new FileReader(tempSort));
                BufferedWriter bw = new BufferedWriter(new FileWriter(applicationFunctions.dataFile));
                String currentLine = br.readLine();
                while (currentLine != null) {
                    bw.write(currentLine);
                    bw.newLine();
                    currentLine = br.readLine();
                }
                br.close();
                bw.close();
                databaseBuild.frame.dispose();
                new databaseBuild();
            } catch (IOException error) {
                System.out.println("Error rewriting the file.");
            }
        }
    }
}
