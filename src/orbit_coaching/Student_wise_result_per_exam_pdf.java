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

public class Student_wise_result_per_exam_pdf {
    boolean flag = false;
    double total_point=0.0;
    String fourth_sub = "";
    String fourth_sub_gpd="";
    int subject_counter = 0;
    String name = "",address = "",contact_number = "";

    Student_wise_result_per_exam_pdf(String yr,String cls,String xm,String fourth_subject,String roll,
                                     String name,String school,String father,String mother,String with_four,String without_four) {
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


            String pdf_name =Calendar.getInstance().getTime().toString()+" exam wise result "+xm+" "+yr+" "+
                    roll+".pdf";
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

            Paragraph p1 = new Paragraph("ACADEMIC/PSC/JSC/SSC/HSC Final Private Program\nAddress : "+address+"\nContact Number : "+
                    contact_number,

                    FontFactory.getFont(FontFactory.TIMES, 14));
            p1.setAlignment(Element.ALIGN_CENTER);
            document.add(p1);

            p1 = new Paragraph("\n",

                    FontFactory.getFont(FontFactory.TIMES, 14));
            p1.setAlignment(Element.ALIGN_CENTER);
            document.add(p1);

            p1 = new Paragraph("ROLL : "+roll+"\n"+"NAME : "+name+"\nFATHER'S NAME : "+father
                    +"\nFATHER'S NAME : "+father+"\n"+"MOTHER'S NAME : "+mother+"\nSCHOOL/COLLEGE : "+school,

                    FontFactory.getFont(FontFactory.TIMES, 14,Font.UNDERLINE));
            p1.setAlignment(Element.ALIGN_CENTER);
            document.add(p1);

            PdfPTable table = new PdfPTable(6);
            table.setSpacingBefore(30);

            // the cell object
            PdfPCell cell;



            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell("SL NO.");
            table.addCell("SUBJECT.");
            table.addCell("MARKS");
            table.addCell("LETTER GRADE");
            table.addCell("GRADE POINT");
            table.addCell("HIGHEST MARKS");


            int counter=1;
            try {

                ResultSet resultSet1 = Database_query.get_marks_for_specific_result(yr, cls, xm, roll);

                resultSet1.beforeFirst();
                while (resultSet1.next()) {

                    ResultSet highest_resultset = Database_query.get_highest_marks_specific(cls, yr,xm, resultSet1.getString(1));
                    String highest = "";

                    try {
                        if (highest_resultset.next()) {
                            highest = highest_resultset.getString(1);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }


                    String mrk = resultSet1.getString(2);
                    String grade_point =Grade_point_calculation.get_grade(mrk);
                    String grade = Double.toString(Grade_point_calculation.get_grade_point(mrk));
                    if(fourth_subject.equals(resultSet1.getString(1)))
                    {
                        fourth_sub =fourth_subject;
                        fourth_sub_gpd = grade_point;
                    }
                    else
                        total_point+=Double.parseDouble(grade);
                    if(grade.equals("F") && !fourth_subject.equals(resultSet1.getString(1)))
                        flag = true;

                    String sub = resultSet1.getString(1);
                    if(sub.equals(fourth_subject))
                        sub+="(4th)";
                    table.addCell(Integer.toString(counter++));
                    table.addCell(sub);
                    table.addCell(mrk);
                    table.addCell(grade_point);
                    table.addCell(grade.substring(0,3));
                    table.addCell(highest);
                }
            }

            catch (Exception ex)
            {
                ex.printStackTrace();
            }

            document.add(table);





            p1.setSpacingBefore(50);
            p1 = new Paragraph("\nGPA With Fourth Subject : "+with_four,
                    FontFactory.getFont(FontFactory.TIMES, 14));
            document.add(p1);

            p1 = new Paragraph("GPA Without Frouth Subject : "+without_four,
                    FontFactory.getFont(FontFactory.TIMES, 14));
            document.add(p1);

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
