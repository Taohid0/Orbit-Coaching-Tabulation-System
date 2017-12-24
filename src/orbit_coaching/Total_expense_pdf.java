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

public class Total_expense_pdf {

    String name = "",address = "",contact_number = "";

    Total_expense_pdf(String yr) {
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


            String pdf_name ="PDF\\"+Calendar.getInstance().getTime().toString()+" Total expense "+yr+".pdf";
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

            p1 = new Paragraph("Total Expense of Year "+yr,
                    FontFactory.getFont(FontFactory.TIMES, 14,Font.UNDERLINE));
            p1.setAlignment(Element.ALIGN_CENTER);
            document.add(p1);

            PdfPTable table = new PdfPTable(7);
            table.setSpacingBefore(30);

            // the cell object
            PdfPCell cell;



            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell("SL NO.");
            table.addCell("ID");
            table.addCell("NAME");
            table.addCell("DATE");
            table.addCell("PURPOSE");
            table.addCell("TYPE");
            table.addCell("AMOUNT");
            int counter=1,total=0;

            try {
                ResultSet resultSet = Database_query.get_expense_year(yr);
                resultSet.beforeFirst();

                while (resultSet.next()) {
                    String name = "";
                    String cnt = Integer.toString(counter);
                    String type = resultSet.getString(5);
                    if (type.equals("Student"))
                        try {
                            ResultSet resultSet1 = Database_query.get_name(resultSet.getString(1));
                            resultSet1.beforeFirst();

                            if (resultSet1.next()) {
                                name = resultSet1.getString(1);
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    else if (type.equals("Teacher"))
                        try {
                            ResultSet resultSet1 = Database_query.get_teacher_name(resultSet.getString(1));
                            resultSet1.beforeFirst();

                            if (resultSet1.next()) {
                                name = resultSet1.getString(1);
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    else
                        name = "";
                    table.addCell(Integer.toString(counter++));
                    table.addCell(resultSet.getString(1));
                    table.addCell(name);
                    table.addCell(resultSet.getString(2));
                    table.addCell(resultSet.getString(3));
                    table.addCell(resultSet.getString(5));
                    table.addCell(resultSet.getString(4));


                    total+=Integer.parseInt(resultSet.getString(4));


                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            document.add(table);




            p1.setSpacingBefore(50);
            p1 = new Paragraph("TOTAL AMOUNT : "+Integer.toString(total),
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
