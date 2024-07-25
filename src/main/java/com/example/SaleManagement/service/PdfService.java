package com.example.SaleManagement.service;

import com.example.SaleManagement.model.Order;
import com.example.SaleManagement.model.OrderDetail;
import com.example.SaleManagement.model.PurchaseOrder;
import com.example.SaleManagement.model.PurchaseOrderItem;
import com.example.SaleManagement.repository.OrdersRepository;
import com.example.SaleManagement.repository.PurchaseOrderRepository;
import com.lowagie.text.*;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Service
public class PdfService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;
    @Autowired
    private OrdersRepository ordersRepository;


    private Logger logger = LoggerFactory.getLogger(PdfService.class);

    public ByteArrayInputStream createPdfPurchaseOrder(int purchaseOrderId) throws DocumentException {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findByIdPurchaseOrder(purchaseOrderId);

        logger.info("Create pdf Started: ");

        String title = "Purchase Order Details";

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Document document = new Document();
        PdfWriter.getInstance(document, out);

        HeaderFooter footer = new HeaderFooter(true);
        footer.setAlignment(Element.ALIGN_CENTER);
        footer.setBorderWidthBottom(0);
        document.setFooter(footer);

        document.open();

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
        Paragraph titlePara = new Paragraph(title, titleFont);
        titlePara.setAlignment(Element.ALIGN_CENTER);
        document.add(titlePara);

        // Create a table in PDF
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        // Add header cells
        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        String[] headers = {"ID", "Name", "Quantity", "Price", "Total"};
        for (String header : headers) {
            PdfPCell cell = new PdfPCell(new Phrase(header, headerFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setPadding(5);
            table.addCell(cell);
        }

        double totalAmount = 0.0;

        // Add data to table
        for (PurchaseOrderItem item : purchaseOrder.getItems()) {
            double itemTotal = item.getQuantityItemOrderPurchase() * item.getPriceOrderPurchase();
            totalAmount += itemTotal;

            PdfPCell cell;

            cell = new PdfPCell(new Phrase(String.valueOf(item.getIdPurchaseOrderItem())));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setPadding(10);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(item.getProduct().getNamePro()));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setPadding(10);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(String.valueOf(item.getQuantityItemOrderPurchase())));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setPadding(10);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(String.valueOf(item.getPriceOrderPurchase())));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setPadding(10);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(String.valueOf(itemTotal)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setPadding(10);
            table.addCell(cell);
        }

        // Add total amount row
        PdfPCell totalCell = new PdfPCell(new Phrase("Total Amount", headerFont));
        totalCell.setColspan(4);
        totalCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        totalCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        totalCell.setPadding(10);
        table.addCell(totalCell);

        PdfPCell totalAmountCell = new PdfPCell(new Phrase(String.valueOf(totalAmount)));
        totalAmountCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        totalAmountCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        totalAmountCell.setPadding(10);
        table.addCell(totalAmountCell);

        document.add(table);

        document.close();

        return new ByteArrayInputStream(out.toByteArray());
    }

    public ByteArrayInputStream createPdfOrder(int orderId) throws DocumentException {
        Order order = ordersRepository.findByIdOrder(orderId);

        logger.info("Create pdf Started: ");

        String title = "Purchase Order Details";

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Document document = new Document();
        PdfWriter.getInstance(document, out);

        HeaderFooter footer = new HeaderFooter(true);
        footer.setAlignment(Element.ALIGN_CENTER);
        footer.setBorderWidthBottom(0);
        document.setFooter(footer);

        document.open();

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
        Paragraph titlePara = new Paragraph(title, titleFont);
        titlePara.setAlignment(Element.ALIGN_CENTER);
        document.add(titlePara);

        // Create a table in PDF
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        // Add header cells
        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        String[] headers = {"ID", "Name", "Quantity", "Price", "Total"};
        for (String header : headers) {
            PdfPCell cell = new PdfPCell(new Phrase(header, headerFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setPadding(5);
            table.addCell(cell);
        }

        double totalAmount = 0.0;

        // Add data to table
        for (OrderDetail item : order.getItems()) {
            double itemTotal = item.getQuantity() * item.getPrice();
            totalAmount += itemTotal;

            PdfPCell cell;

            cell = new PdfPCell(new Phrase(String.valueOf(item.getIdOrderDetail())));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setPadding(10);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(item.getProduct().getNamePro()));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setPadding(10);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(String.valueOf(item.getQuantity())));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setPadding(10);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(String.valueOf(item.getPrice())));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setPadding(10);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(String.valueOf(itemTotal)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setPadding(10);
            table.addCell(cell);
        }

        // Add total amount row
        PdfPCell totalCell = new PdfPCell(new Phrase("Total Amount", headerFont));
        totalCell.setColspan(4);
        totalCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        totalCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        totalCell.setPadding(10);
        table.addCell(totalCell);

        PdfPCell totalAmountCell = new PdfPCell(new Phrase(String.valueOf(totalAmount)));
        totalAmountCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        totalAmountCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        totalAmountCell.setPadding(10);
        table.addCell(totalAmountCell);

        document.add(table);

        document.close();

        return new ByteArrayInputStream(out.toByteArray());
    }

}
