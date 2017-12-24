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

public class Total_income_other_pdf_creation_coaching_pad {

    String name = "",address = "",contact_number = "";

    Total_income_other_pdf_creation_coaching_pad(String yr) {
        try {

            Document document = new Document(PageSize.A4, 50, 50, 50, 50);

            String pdf_name ="PDF\\"+Calendar.getInstance().getTime().toString()+" total other income_coaching_pad.pdf";
            pdf_name=pdf_name.replace(':','_');
            System.out.println(pdf_name);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdf_name));

            document.open();


            //anchorTarget.setName("BackToTop");
            Paragraph paragraph1 = new Paragraph();

            paragraph1.setSpacingBefore(50);

            document.add(paragraph1);

            Paragraph p1 = new Paragraph("\n\n\n\n\n",

                    FontFactory.getFont(FontFactory.TIMES, 14));
            p1.setAlignment(Element.ALIGN_CENTER);
            document.add(p1);

            p1 = new Paragraph("\n",

                    FontFactory.getFont(FontFactory.TIMES, 14));
            p1.setAlignment(Element.ALIGN_CENTER);
            document.add(p1);

            p1 = new Paragraph("Total income of "+yr+" (From Other Sources): \n",

                    FontFactory.getFont(FontFactory.TIMES, 14,Font.UNDERLINE));
            p1.setAlignment(Element.ALIGN_CENTER);
            document.add(p1);

            PdfPTable table = new PdfPTable(5);
            table.setSpacingBefore(30);

            // the cell object
            PdfPCell cell;



            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell("SL NO.");
            table.addCell("FROM");
            table.addCell("PURPOSE");
            table.addCell("DATE");
            table.addCell("AMOUNT");
            int total = 0;

            try
            {

                ResultSet resultSet = Database_query.get_other_income_details(yr);
                resultSet.beforeFirst();
                int counter=1;
                while (resultSet.next())
                {
                    table.addCell(Integer.toString(counter++));

                    String from = resultSet.getString(1);

                    try
                    {
                        ResultSet resultSet1 = Database_query.get_name_without_commnecttion(from);
                        resultSet1.beforeFirst();

                        if(resultSet1.next())
                        {
                            from+="("+resultSet1.getString(1)+")";
                        }
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }
                    table.addCell(from);
                    table.addCell(resultSet.getString(2));

                    table.addCell(resultSet.getString(3));
                    table.addCell(resultSet.getString(4));
                    total+=Integer.parseInt(resultSet.getString(4));
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }

            document.add(table);


            p1.setSpacingBefore(50);
            p1 = new Paragraph("\nTOTAL AMOUNT = "+Integer.toString(total),
                    FontFactory.getFont(FontFactory.TIMES, 14));
            document.add(p1);



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
