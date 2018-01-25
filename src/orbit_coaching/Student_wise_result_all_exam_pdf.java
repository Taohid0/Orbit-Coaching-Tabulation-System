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

public class Student_wise_result_all_exam_pdf {
    boolean flag = false;
    double total_point=0.0;
    String fourth_sub = "";
    String fourth_sub_gpd="";
    int subject_counter = 0;
    String name = "",address = "",contact_number = "";

    Student_wise_result_all_exam_pdf(String yr,String cls,String reg_number,String st_name) {
        try {
            try
            {
                ResultSet resultSet = Database_query.get_institution_info();
                resultSet.beforeFirst();

                while (resultSet.next())
                {
                    name = resultSet.getString(1);
                    address = resultSet.getString(2);
                    contact_number = resultSet.getString(3);
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);


            String pdf_name ="PDF\\"+Calendar.getInstance().getTime().toString()+" Student wise result "+reg_number+
                    " "+yr+" "+
                    name+".pdf";
            pdf_name=pdf_name.replace(':','_');
            System.out.println(pdf_name);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdf_name));

            document.open();

            Anchor anchorTarget = new Anchor(name,FontFactory.getFont(FontFactory.TIMES, 24, Font.BOLD));

            //anchorTarget.setName("BackToTop");
            Paragraph paragraph1 = new Paragraph();

            paragraph1.setSpacingBefore(50);
            paragraph1.add(anchorTarget);paragraph1.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph1);

            Paragraph p1 = new Paragraph("ACADEMIC/PEC/JSC/SSC/HSC Final Private Program\nAddress : "+address+"\nContact Number : "+
                    contact_number,

                    FontFactory.getFont(FontFactory.TIMES, 14));
            p1.setAlignment(Element.ALIGN_CENTER);
            document.add(p1);

            p1 = new Paragraph("\n",

                    FontFactory.getFont(FontFactory.TIMES, 14));
            p1.setAlignment(Element.ALIGN_CENTER);
            document.add(p1);

            p1 = new Paragraph("REGISTRATION NUMBER : "+reg_number+"\n"+"NAME : "+st_name+"\nYEAR : "+yr,

                    FontFactory.getFont(FontFactory.TIMES, 14,Font.UNDERLINE));
            p1.setAlignment(Element.ALIGN_CENTER);
            document.add(p1);

            PdfPTable table = new PdfPTable(6);
            table.setSpacingBefore(30);

            // the cell object
            PdfPCell cell;



            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            //table.addCell("SL NO.");
            table.addCell("EXAM");
            table.addCell("SUBJECT");
            table.addCell("DATE");
            table.addCell("OBTAINED MARKS");
            table.addCell("HIGHEST MARKS");
            table.addCell("TOTAL");


            int counter=1;
            try
            {
                ResultSet resultSet = Database_query.get_student_wise_marks(yr,cls,reg_number);

                resultSet.beforeFirst();

                while (resultSet.next())
                {
                    String highest_marks = "";
                    String out_of = "";

                    try
                    {

                        String c= cls;
                        String e = resultSet.getString(1);
                        String s=resultSet.getString(2);
                        String d =resultSet.getString(3);

                        ResultSet highest_marks_result = Database_query.get_highest_marks2(e,s,c,d);
                        highest_marks_result.beforeFirst();

                        if(highest_marks_result.next())
                        {
                            highest_marks = highest_marks_result.getString(1);
                        }
                        try
                        {
                            ResultSet out_of_resultset = Database_query.get_out_of_marks2(e,s,c,d);
                            out_of_resultset.beforeFirst();
                            if(out_of_resultset.next())
                            {
                                out_of=  out_of_resultset.getString(1);
                            }

                        }
                        catch (Exception ex)
                        {
                            ex.printStackTrace();
                        }
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }

                    String obtaind_marks = "Absent";
                    try
                    {
                        if(!resultSet.getString(4).equals("-1"))
                        {
                            obtaind_marks = resultSet.getString(4);
                        }
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }
                    //table.addCell(Integer.toString(counter++));
                    table.addCell(resultSet.getString(1));
                    table.addCell(resultSet.getString(2));
                    table.addCell(resultSet.getString(3));
                    table.addCell(obtaind_marks);
                    table.addCell(highest_marks);
                    table.addCell(out_of);

                }
            }

            catch (Exception ex)
            {
                ex.printStackTrace();
            }

            document.add(table);



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
