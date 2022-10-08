package com.example.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Test {
 
   public static void main(String args[]) {
       // Инициализация объекта date
       Date date = new Date();
        
       // Вывод отформатированной даты (удалите знак = ниже из кода, а то у меня конфликтует этот код с html)
       System.out.printf( date.toString());

       SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
       String str = args.length == 0 ? "2011-11-11" : args[0];

       System.out.print("Строка " + str + " распаршена как ");
       Date parsingDate;
       try {
           parsingDate = ft.parse(str);
           System.out.println(parsingDate);
       }catch (ParseException e) {
           System.out.println("Нераспаршена с помощью " + ft);
       }
       LocalDate date1 = LocalDate.now(); // получаем текущую дату
       System.out.println("LocalDate "+date1);

       date1= LocalDate.of(2000,11,05);
       System.out.println("LocalDate "+date1);

       date1=  date1.minusMonths(2);
       System.out.println("LocalDate "+date1);
   }
}