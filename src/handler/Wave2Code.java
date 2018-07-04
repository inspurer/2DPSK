/**
 * 原创声明:csu_xiaotao@163.com
 */
package handler;

import javax.swing.JOptionPane;

import model.CodeElement;
import util.WaveDrawer;

/**
 * 新建类声明
 * 
 * @author csu_xiaotao <a href = "https://github.com/inspurer">月小水长</a>
 *         下午6:35:35 2018年7月4日
 */
public class Wave2Code {
	static double[] bx, by;
	static double[] cx, cy;
	static double[] dx, dy;
	static int[] rcodes,acodes; //相对/绝对码

	public Wave2Code(int codeLength) {

		bx = new double[codeLength * CodeElement.pointNumberOfPerCode];
		by = new double[codeLength * CodeElement.pointNumberOfPerCode];
		cx = new double[codeLength * CodeElement.pointNumberOfPerCode];
		cy = new double[codeLength * CodeElement.pointNumberOfPerCode];
		dx = new double[codeLength * CodeElement.pointNumberOfPerCode];
		dy = new double[codeLength * CodeElement.pointNumberOfPerCode];
		rcodes = new int[codeLength];
		acodes = new int[codeLength];
	}

	public static void b() {
		double deltaX = 2 * Math.PI / CodeElement.pointNumberOfPerCode;
		for (int i = 0; i < bx.length; i++) {
			bx[i] = deltaX * i;
			by[i] = Math.sin(bx[i]);
		}
		WaveDrawer.dataViewer(bx, by, "b点处波形");
	}

	public static void c(double[] ay) {
		for (int i = 0; i < bx.length; i++) {
			cx[i] = bx[i];
			cy[i] = ay[i]*by[i];
		}
		WaveDrawer.dataViewer(cx, cy, "c点处波形");
	}

	public static void d() {
		int randomPoint = 100; // 根据在200点左右的正负来实现‘滤波’
		double deltaX = 2 * Math.PI / CodeElement.pointNumberOfPerCode;
		for (int i = 0; i < dx.length; i++) {
			dx[i] = deltaX * i;
		}

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < rcodes.length; i++) {
			// 得到绝对码和d点波形
			if (cy[randomPoint + CodeElement.pointNumberOfPerCode * i] > 0) {
				rcodes[i] = 0;

				for (int j = 0; j < CodeElement.pointNumberOfPerCode; j++) {
					dy[j + i * CodeElement.pointNumberOfPerCode] = 2.0/3;
				}
			} else {
				rcodes[i] = 1;

				for (int j = 0; j < CodeElement.pointNumberOfPerCode; j++) {
					dy[j + i * CodeElement.pointNumberOfPerCode] = -2.0/3;
				}
			}

			// 相对码转绝对码
			if (i == 0) {
				acodes[0] = Math.abs(rcodes[0] - 0);
			} else {
				acodes[i] = Math.abs(rcodes[i] - rcodes[i - 1]);
			}
			
			sb.append(acodes[i]+"");

		}
		WaveDrawer.dataViewer(dx, dy, "d点处的波形");
		JOptionPane.showMessageDialog(null, sb.toString(), "解调出的信号为",JOptionPane.INFORMATION_MESSAGE);
	}
}
