
package texteditor;

import java.util.Scanner;
public class textEditor {
    static char[] textArray =new char[0];
    //static değişken methoda class arasına yazdık
    static char[] originalTextArray =new char[0];//original olan kalsın diye
    
    public static void main(String[] args) {
       Scanner input =new Scanner(System.in);
        
       
    boolean isRunning=true;
    //enüyü ve if kısımları 
     while(isRunning){
         
         System.out.println("\n------Text Editor Menu------");
         System.out.println("1.Enter a new text");
         System.out.println("2.Print the current text");
         System.out.println("3.Show statistical information ");
         System.out.println("4.Convert to lowercase");
         System.out.println("5.Convert to uppercase");
         System.out.println("6.Capitalize each word");
         System.out.println("7.Reverse all characters");
         System.out.println("8.Reverse all words");
         System.out.println("9.Insert a new word");
         System.out.println("10.Delete a word");
         System.out.println("11.Return the original text.");
         System.out.println("12.Exit ");
         System.out.println("Choose an option:   ");
         
           int option=input.nextInt();
           input.nextLine();
           
           
      if (option==1) {
          enterAText(input);// userdan text almam gerekiyor  
           
      }else if (option==2){
          printText();
      
      }else if (option==3){
          getStatistics();
          
      }else if (option==4){
          makeLowercase();
          
      }else if (option==5){
          makeUppercase();
          
      }else if (option==6){
          capitalizeEachWord();
          
      }else if (option==7){
          reverseAllCharacters();
          
      }else if (option==8){
          reverseAllWords();
          
      }else if (option==9){
          insertAWord();
          
      }else if (option==10){
          deleteAWord();
          
      }else if (option==11){// reverse insert delete sonrası  
                            //kalıcı devam ettiği için bu seçeneği koydum
         textArray = originalTextArray;
         System.out.println("Returned to original text.");
         printText();
         
      }else if (option==12){
          quit();
      }        
         
     }//while döngüsü kapanış
        
   }//main methodu kapanış
   
   public static void enterAText(Scanner input){
       
       System.out.println("Enter your text(1-1024 characters):     ");
       String userInput=input.nextLine();
       
       //uzunluk eğer yanlışsa
       if (userInput.length()<1 || userInput.length()>1024){
          System.out.println("Invalid text length.");
          return; 
        
       }
       
       //izin verilen karakterler
       for(int i=0;i<userInput.length();i++){
          char ch=userInput.charAt(i);
           
          if(!(Character.isLetterOrDigit(ch)|| ch== ' ' || ch=='*'||ch=='+'
                  ||ch==','||ch=='-'||ch=='.'||ch=='/')){
              System.out.println("Invalid character found: '" + ch + "'");
              return;
           }
      
         }
         //her şey doğruysa
         
         textArray=userInput.toCharArray();
         originalTextArray = new char[userInput.length()];
           for (int i = 0; i < userInput.length(); i++) {
              originalTextArray[i] = userInput.charAt(i);
            }
         
         System.out.println("Text saved.");
       
    } //enterAText kapanış
   
   public static void printText(){
       if(textArray.length==0) {
           System.out.println("No text has been entered.");
           return;
         }
       
     //karakterleri sırayla yazdırdık
     
       for(int i=0;i< textArray.length;i++){
           System.out.print(textArray[i]);
         
       }
      System.out.println();//alt satır için
       
     
    }//printText kapanış
   
   public static void getStatistics(){
       
       if(textArray.length==0){
        System.out.println("No text has been entered.");
        return;
              
        }
       
     //değişken başlangıçları
     
     int digitCount=0;
     int uppercaseCount=0;
     int lowercaseCount=0;
     int symbolCount=0;
     int spaceCount=0;
     int wordCount=0;
     
     for(int i=0;i<textArray.length;i++){
         char ch=textArray[i];
         
         if(Character.isDigit(ch)){
             digitCount++;
         }else if(Character.isUpperCase(ch)){
             uppercaseCount++;
         }else if(Character.isLowerCase(ch)){
             lowercaseCount++;
         }else if("*+,-./".indexOf(ch)>=0){
             symbolCount++;
         }else if(ch== ' '){
             spaceCount++;
         }
     }
         
       if(spaceCount==0 && textArray.length>0){
           wordCount=1;
       } else{
           wordCount=spaceCount+1;
       }
       
    System.out.println("---Statistics---");
    System.out.println("Numerical characters: " +digitCount);    
    System.out.println("Uppercase letters: " + uppercaseCount);     
    System.out.println("Lowercase letters: "+ lowercaseCount);     
    System.out.println("Symbols: "+ symbolCount);     
    System.out.println("Spaces: "+spaceCount);     
    System.out.println("Words: "+wordCount); 
             
   } //get static kapanış
   
   public static void makeLowercase(){
      if(textArray.length==0){
        System.out.println("No text has been entered.");
          return;    
        }
      
      for(int i=0;i<textArray.length;i++){
          if(Character.isUpperCase(textArray[i])){
              
            textArray[i]=Character.toLowerCase(textArray[i]);
          }
      }
      System.out.println("Result:   ");
      printText();
       
   }//make lowercase kapanış
    
   public static void makeUppercase(){
      if(textArray.length==0){
        System.out.println("No text has been entered.");
          return;    
        }
      
      for(int i=0;i<textArray.length;i++){
          if(Character.isLowerCase(textArray[i])){
              
            textArray[i]=Character.toUpperCase(textArray[i]);
          }
      }
      System.out.println("Result:  ");
      printText();
       
   } //make upper case kapanış
    
   public static void capitalizeEachWord(){
       if(textArray.length==0){
        System.out.println("No text has been entered.");
          return;    
        }
       
      //ilk harfse büyük
      //boşluktan hemen sonra büyük
      //diğerleri küçük olmalı
     boolean newWord=true; 
      
     for(int i=0;i<textArray.length;i++){
         char ch=textArray[i];
         
         if(ch== ' '){
             newWord=true;//boşluk sonrası büyük olucak demektir
         }else if(newWord&&Character.isLetter(ch)){
             textArray[i]=Character.toUpperCase(ch);
             newWord=false;//diğer harfleri büyütmemek için
         }else{
             //diğer harfler küçük
             textArray[i]=Character.toLowerCase(ch);
         }
         
     }
      
     System.out.println("Result:   ");
     printText();
       
   }//capitalize kapanış 
    
   public static void reverseAllCharacters(){
       if(textArray.length==0){
        System.out.println("No text has been entered.");
          return;    
        }
       
       int left=0;//dizi başı
       int right=textArray.length-1;//dizi sonu
       
       while (left<right){
           
           char temp=textArray[left];
           textArray[left]=textArray[right];
           textArray[right]=temp;
           
           
           left++;
           right--;
       }
       
      System.out.println("Result:  ");
      printText();
       
   }//reverse character kapanış
   
   public static void reverseAllWords(){
       if(textArray.length==0){
        System.out.println("No text has been entered.");
          return;    
        }
       
      String word= "";
      String reversedText= "";
      
      
      for(int i=0;i<textArray.length;i++){
          char ch=textArray[i];
          
          if(ch != ' '){//boşluk yoksa kelime hala oluşmadı 
              word+=ch;
          }else{ //kelime tamamlandığı için önüne getiriyoruz.
              reversedText= " "+word+ reversedText;
              word= "";
          }
      }
       
      reversedText=word + reversedText;
      
      textArray=reversedText.toCharArray();
      System.out.println("Result:  ");
      printText();
   }//reverse words kapanış
   
   public static void insertAWord(){
       if(textArray.length==0){
        System.out.println("No text has been entered.");
          return;    
        }
       
       Scanner input=new Scanner(System.in);
       
       System.out.println("Enter to word to insert:   ");
       String inWord=input.nextLine();
       
       System.out.println("Enter the position(after which word):   ");
       int position=input.nextInt();
       input.nextLine();
       
       
       //boşluk sayıcaz
       int spaceCount=0;
       int insertIndex=textArray.length;//sona eklenmiş gbi kullanıcaz
       
       for(int i=0;i<textArray.length;i++){
           if(textArray[i]== ' '){
               spaceCount++;
               if(spaceCount==position){
                   insertIndex=i+1;//boşluk sonrasına ekleme
                   break;
               }
           }
       }
       //+1 boşluk saydık
    int newLength=textArray.length+1+inWord.length();
    char[] newArray=new char[newLength];//boş karakter dizisi
    
    for (int i=0;i< insertIndex;i++){
        newArray[i]=textArray[i];
    }
       
    for(int i=0;i<inWord.length();i++){
        newArray[insertIndex+i]=inWord.charAt(i);
        
    }   
      
    newArray[insertIndex+inWord.length()]= ' ';
    
    
    for(int i=insertIndex;i<textArray.length;i++){
        newArray[i+inWord.length()+1]=textArray[i];
    }
       
    textArray=newArray;
    
    System.out.println("Result:   ");
    printText();
    
   }//insert kapanış
   
   public static void deleteAWord(){
     if(textArray.length==0){
        System.out.println("No text has been entered.");
          return;    
        }
     
     Scanner input=new Scanner(System.in);
       
       
       System.out.println("Enter the position of the word to delete"
               + " (example:for the first word(1)):   ");
       int position=input.nextInt();
       input.nextLine();
       
       
     int spaceCount= 0;
     int deleteIndex =textArray.length;
     
     for(int i=0;i<textArray.length;i++){
         if(textArray[i]== ' '){
             spaceCount++;
             if(spaceCount==position-1){
                 deleteIndex=i+1;
                         break;
             }
         } 
     }
       
     if (position == 1 && spaceCount == 0) {
        deleteIndex = 0; // ilk kelime
    }

    
    int wordLength = 0;
    for (int i = deleteIndex; i < textArray.length && textArray[i] != ' '; i++) {
        wordLength++;
    }

    
    int newLength = textArray.length - wordLength;

    // Kelime ortadaysa boşluğu da sil
    if (deleteIndex + wordLength < textArray.length) {
        newLength--; // boşluğu da çıkar
    }

    char[] newArray = new char[newLength];
    int j = 0;

   
    for (int i = 0; i < textArray.length; i++) {
        
        if (i == deleteIndex) {
            i += wordLength;
            if (i < textArray.length && textArray[i] == ' ') {
                i++; 
            }
        }

        if (i < textArray.length) {
            newArray[j++] = textArray[i];
        }
    }

    
    textArray = newArray;

    System.out.println("Result:");
    printText();  
       
    
   }//delete kapanış
   
   public static void quit(){
       
       
       System.out.println("Exiting the program.");
       System.exit(0);
   }     
    
    
    
}

