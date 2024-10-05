
/*
Question 1: File System Structure (Tree-Based Problem)
Problem Statement: You are given a nested dictionary representing a file system. Each directory can contain subdirectories or files, and each file has a size. Write a function that calculates the total size of all files in a specified subdirectory, which can be given in dot notation (e.g., "root.dir1" or "root.dir2.dir3"). If a specified directory does not exist, return a message indicating that the directory was not found. If the directory has no files, its size is zero. The function should traverse the directory structure and return the total size of all files in the specified subdirectory.
Input-1: 
path = "root.dir1.subdir1"
Expected Output-1:
Total size: 1000


Input-2: 
path = "root.dir2.subdir4.subsubdir5"
Expected Output-2:
Total size: 3400
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileSystem {

    // Function to calculate the total size of all files in the specified
    // subdirectory
    public static int getTotalSize(Map<String, Object> fileSystem, String path) {
        String[] directories = path.split("\\.");
        Map<String, Object> currentDirectory = fileSystem;

        // Traverse the directories according to the path
        for (String dir : directories) {
            if (currentDirectory.containsKey(dir)) {
                Object value = currentDirectory.get(dir);
                if (value instanceof Map) {
                    currentDirectory = (Map<String, Object>) value; // Navigate to the subdirectory
                } else {
                    return 0; // Reached a file instead of a directory
                }
            } else {
                return -1; // Directory not found
            }
        }

        // Calculate the total size of the current directory
        return calculateSize(currentDirectory);
    }

    // Helper function to calculate the total size of a directory
    private static int calculateSize(Map<String, Object> directory) {
        int totalSize = 0;

        for (Map.Entry<String, Object> entry : directory.entrySet()) {
            Object value = entry.getValue();

            if (value instanceof Integer) {
                // If it's a file, add its size
                totalSize += (Integer) value;
            } else if (value instanceof Map) {
                // If it's a subdirectory, calculate its size recursively
                totalSize += calculateSize((Map<String, Object>) value);
            }
        }

        return totalSize;
    }

    public static void main(String[] args) {
        // Example file system structure (nested dictionary)
        Map<String, Object> fileSystem = new HashMap<>();
        fileSystem.put("root", new HashMap<String, Object>() {
            {
                put("dir1", new HashMap<String, Object>() {
                    {
                        put("subdir1", new HashMap<String, Object>() {
                            {
                                put("file1.txt", 100);
                                put("file2.txt", 200);
                                put("subsubdir1", new HashMap<String, Object>() {
                                    {
                                        put("file3.txt", 50);
                                        put("file4.txt", 150);
                                        put("subsubsubdir1", new HashMap<String, Object>() {
                                            {
                                                put("file5.txt", 500);
                                                put("emptydir1", new HashMap<String, Object>() {
                                                });
                                            }
                                        });
                                    }
                                });
                            }
                        });
                        put("subdir2", new HashMap<String, Object>() {
                            {
                                put("file6.txt", 300);
                                put("subsubdir2", new HashMap<String, Object>() {
                                    {
                                        put("file7.txt", 700);
                                        put("subsubsubdir2", new HashMap<String, Object>() {
                                            {
                                                put("file8.txt", 800);
                                                put("file9.txt", 900);
                                            }
                                        });
                                    }
                                });
                                put("emptydir2", new HashMap<String, Object>() {
                                });
                            }
                        });
                        put("file10.txt", 1000);
                    }
                });
                put("dir2", new HashMap<String, Object>() {
                    {
                        put("subdir3", new HashMap<String, Object>() {
                            {
                                put("subsubdir3", new HashMap<String, Object>() {
                                    {
                                        put("file11.txt", 400);
                                        put("file12.txt", 500);
                                    }
                                });
                                put("subsubdir4", new HashMap<String, Object>() {
                                    {
                                        put("emptydir3", new HashMap<String, Object>() {
                                        });
                                    }
                                });
                            }
                        });
                        put("subdir4", new HashMap<String, Object>() {
                            {
                                put("file13.txt", 600);
                                put("subsubdir5", new HashMap<String, Object>() {
                                    {
                                        put("file14.txt", 700);
                                        put("file15.txt", 800);
                                        put("subsubsubdir3", new HashMap<String, Object>() {
                                            {
                                                put("emptydir4", new HashMap<String, Object>() {
                                                });
                                                put("file16.txt", 900);
                                                put("file17.txt", 1000);
                                            }
                                        });
                                    }
                                });
                            }
                        });
                        put("emptydir5", new HashMap<String, Object>() {
                        });
                    }
                });
                put("dir3", new HashMap<String, Object>() {
                    {
                        put("file18.txt", 1100);
                        put("subdir5", new HashMap<String, Object>() {
                            {
                                put("subsubdir6", new HashMap<String, Object>() {
                                    {
                                        put("file19.txt", 1200);
                                        put("subsubsubdir4", new HashMap<String, Object>() {
                                            {
                                                put("file20.txt", 1300);
                                                put("emptydir6", new HashMap<String, Object>() {
                                                });
                                            }
                                        });
                                    }
                                });
                            }
                        });
                    }
                });
                put("emptydir7", new HashMap<String, Object>() {
                });
                put("file21.txt", 1400);
            }
        });

        // Create a Scanner object to read input
        Scanner scanner = new Scanner(System.in);

        // Ask user for the path
        System.out.print("Enter the path (e.g., root.dir1.subdir1): ");
        String userPath = scanner.nextLine().trim(); // Added trim to avoid whitespace issues

        // Calculate the total size
        int size = getTotalSize(fileSystem, userPath);

        // Check the output based on size
        if (size == -1) {
            System.out.println("Directory not found");
        } else {
            System.out.println("Total size: " + size);
        }

        // Close the scanner
        scanner.close();
    }
}
