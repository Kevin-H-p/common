package com.kevin.common.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.util.Hashtable;

/**
 * @author kevin
 * @date 2022-01-17 10:23
 */

public class QRCodeUtil {
    private static final String CHARSET = "utf-8";
    private static final String FORMAT_NAME = "JPG";
    // 二维码尺寸
    private static final int QRCODE_SIZE = 500;
    // LOGO宽度
    private static final int WIDTH = 60;
    // LOGO高度
    private static final int HEIGHT = 60;

    private static final int BLACK = 0xFF000000;

    private static final int WHITE = 0xFFFFFFFF;

    public static void encode(String content, String text, OutputStream output) throws Exception {
        encode(content, text, null, output, false);
    }

    public static void encode(String content, String text, String imgPath, OutputStream output) throws Exception {
        encode(content, text, imgPath, output, true);
    }

    public static void encode(String content, String text, String imgPath, OutputStream output, boolean needCompress)
            throws Exception {
        BufferedImage image = createImage(content, text, imgPath, needCompress);
        ImageIO.write(image, FORMAT_NAME, output);
    }

    private static BufferedImage createImage(String content, String text, String imgPath, boolean needCompress) throws Exception {
        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE, hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? BLACK : WHITE);
            }
        }
        if (StringUtils.isEmpty(imgPath)) {
            return image;
        }
        // 插入图片
        insertImage(image, text, imgPath, needCompress);
        return image;
    }

    private static void insertImage(BufferedImage source, String text, String imgPath, boolean needCompress) throws Exception {
        File file = new File(imgPath);
        if (!file.exists()) {
            return;
        }
        Image src = ImageIO.read(new File(imgPath));
        int width = src.getWidth(null);
        int height = src.getHeight(null);
        if (needCompress) { // 压缩LOGO
            if (width > WIDTH) {
                width = WIDTH;
            }
            if (height > HEIGHT) {
                height = HEIGHT;
            }
            Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图
            g.dispose();
            src = image;
        }

        Graphics2D graph = source.createGraphics();
        int x = (QRCODE_SIZE - width) / 2;
        int y = (QRCODE_SIZE - height) / 2;
        graph.drawImage(src, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);

        if (!StringUtils.isEmpty(text)) {
            Font font = new Font("宋体", Font.PLAIN, 26);
            graph.setFont(font);
            graph.setColor(Color.black);
            // 获取当前文字的对象
            FontMetrics metrics = graph.getFontMetrics(font);

            // 文字在图片中的坐标 这里设置在中间
            int startX = (source.getWidth() - metrics.stringWidth(text)) / 2; //当前文字对象到横坐标（X）的距离
            int startY = source.getHeight() - 2; //当前文字对象到纵坐标（Y）的距离
            graph.drawString(text, startX, startY);
        }
        graph.dispose();
    }


}