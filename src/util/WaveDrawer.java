/**
 * 原创声明:csu_xiaotao@163.com
 */
package util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 新建类声明
 * 
 * @author csu_xiaotao <a href = "https://github.com/inspurer">月小水长</a>
 *         下午3:56:35 2018年7月4日
 */
public class WaveDrawer extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double[] x, y;

	public WaveDrawer(double[] x, double[] y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		for (int i = 0; i < x.length - 1; ++i) {
			g2d.drawLine(adapterForXAxis(x[i]), adapterForYAxis(y[i]), adapterForXAxis(x[i + 1]),
					adapterForYAxis(y[i + 1]));

			if ((i + 2) % 1000 == 0) {
//				Stroke dash = new BasicStroke(1.5f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND,
//						1.5f,new float[]{5,5,},0f);
//			    g2d.setStroke(dash);   //画虚线
				g2d.setColor(new Color(255,0,0));  //红色
				g2d.drawLine(adapterForXAxis(x[i + 1]), 0, 
						adapterForXAxis(x[i + 1]), 100);
				g2d.setColor(new Color(0,0,0));    //黑色
			}
			
			if(i + 2 == x.length) {
				g2d.setColor(new Color(255,0,0));  //红色
				g2d.drawLine(0, adapterForYAxis(0), 
						adapterForXAxis(x[i + 1]), adapterForYAxis(0));
				g2d.setColor(new Color(0,0,0));    //黑色
			}

		}

	}

	/**
	 * 规一化y轴方向的值.
	 *
	 * @param value
	 * @param height
	 */
	// 面板高100px(其中bar占去JFrame的40px),图形高80px,宽600px;
	// 正常波形高2,长约60(10个码元),限于屏幕大小不得已采用非等比扩大
	private int adapterForYAxis(double value) {
		// 坐标变换
		return (int) ((1 - value) * 40) + 10;
	}

	private int adapterForXAxis(double value) {
		return (int) (value * 10);
	}

	public static void dataViewer(double[] x, double[] y, String title) {
		JFrame frame = new JFrame(title);

		frame.getContentPane().add(new WaveDrawer(x, y));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 140);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
