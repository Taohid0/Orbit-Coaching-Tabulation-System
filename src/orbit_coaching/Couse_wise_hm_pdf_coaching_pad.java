package orbit_coaching;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.scene.text.TextAlignment;

import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.util.Calendar;

public class Couse_wise_hm_pdf_coaching_pad {


    Couse_wise_hm_pdf_coaching_pad(String cls,String exam_type,String date,String subject,String total,String highest,String lowest,
                      String average) {
        try {

            Document document = new Document(PageSize.A4, 50, 50, 50, 50);


            String pdf_name ="PDF\\"+Calendar.getInstance().getTime().toString()+" Course wise result name_wise coaching_pad "+exam_type+" "+subject+".pdf";
            pdf_name=pdf_name.replace(':','_');
            System.out.println(pdf_name);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdf_name));

            document.open();

            Paragraph paragraph1 = new Paragraph();

            paragraph1.setSpacingBefore(50);
            document.add(paragraph1);

            Paragraph p1 = new Paragraph("\n\n\n",

                    FontFactory.getFont(FontFactory.TIMES, 14));
            p1.setAlignment(Element.ALIGN_CENTER);
            document.add(p1);

            p1 = new Paragraph("\n",

                    FontFactory.getFont(FontFactory.TIMES, 14));
            p1.setAlignment(Element.ALIGN_CENTER);
            document.add(p1);

            p1 = new Paragraph("EXAMINATION : "+exam_type+"\n"+"SUBJECT : "+subject+"\nDATE : "+date
                    +"\nHIGHEST MARKS : "+highest+"\n"+"LOWEST MARKS : "+lowest+"\nAVERAGE MARKS : "+average,

                    FontFactory.getFont(FontFactory.TIMES, 14,Font.UNDERLINE));
            p1.setAlignment(Element.ALIGN_CENTER);
            document.add(p1);

            PdfPTable table = new PdfPTable(4);
            table.setSpacingBefore(30);

            // the cell object
            PdfPCell cell;



            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell("SL NO.");
            table.addCell("REG NO.");
            table.addCell("ROLL NO.");
            //table.addCell("NAME");
            table.addCell("OBTAINED MARKS");


            int counter=1;
            try
            {
                ResultSet resultSet = Database_query.get_course_wise_marks_for_specific_date_name_wise(cls, exam_type,date,subject);
                resultSet.beforeFirst();

                while (resultSet.next())
                {

                    String name="";
                    String temp_roll="";
                    try {
                        ResultSet name_result = Database_query.get_name(resultSet.getString(1));
                        name_result.beforeFirst();
                        if(name_result.next())
                        {
                            name=name_result.getString(1);
                        }
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }

                    try {
                        ResultSet temp_r_result = Database_query.get_roll_from_registration_number(resultSet.getString(1));
                        temp_r_result.beforeFirst();
                        if(temp_r_result.next())
                        {
                            temp_roll=temp_r_result.getString(1);
                        }
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }
                    String obtained_marks="Absent";
                    if(!resultSet.getString(3).equals("-1"))
                    {
                        obtained_marks = resultSet.getString(3);
                    }
                    table.addCell(Integer.toString(counter++));
                    table.addCell(resultSet.getString(1));
                    table.addCell(temp_roll);
                    //table.addCell(name);
                    table.addCell(obtained_marks);


                }
                resultSet.beforeFirst();
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }

            document.add(table);




            p1.setSpacingBefore(50);
            String date_time = Calendar.getInstance().getTime().toString();
            p1 = new Paragraph("TIME : "+date_time,
                    FontFactory.getFont(FontFactory.TIMES, 14));
            document.add(p1);

            p1 = new Paragraph("\nSIGNATURE : ",
                    FontFactory.getFont(FontFactory.TIMES, 14));
            document.add(p1);

            document.close();

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }


    }
}
