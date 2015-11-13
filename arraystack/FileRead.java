import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileRead {
    public static long t;
    public static long before = 0;
    	public static long after = 0;
    public FileRead() throws Exception
		
	{
	
    	read();
    	
    	//System.out.println("Time passed in ms: " + (after - before));
	//	readfile();
	}
    
    
    
    public static void read() {
        
        BufferedReader br = null;
        try {
            int i = 0;
            br = new BufferedReader(new FileReader("big.txt"));

            while (br.readLine() != null) {
                i = i + 1;
            }

          //  System.out.println(i);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    public static long get_time()
    {
        
        before = System.currentTimeMillis(); 
    	//row=0;
    	read();
    	after = System.currentTimeMillis();
        return t=(after-before);
    }
    
    public static void main(String[] args) 
    {
    //	long before = 0;
    //	long after = 0;
    	//data_list = new Data[1000];
    	//before = System.currentTimeMillis(); 
    	//readfile();
    	//after = System.currentTimeMillis();
    	System.out.println("Time passed in ms: " + get_time());
    }
    
    
}
