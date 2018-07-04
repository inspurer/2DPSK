/**
 * 原创声明:csu_xiaotao@163.com
 */
package handler;

import javax.swing.JOptionPane;

import model.CodeElement;
import model.CodeType;
import util.WaveDrawer;

/**
 * 新建类声明
 * 
 * @author csu_xiaotao <a href = "https://github.com/inspurer">月小水长</a>
 *         下午6:21:56 2018年7月4日
 */
public class Code2Wave {

	private int[] codes; // 相对码/绝对码
	private CodeElement[] ces; // 码元数组,一个码元就是一个正弦系函数
	private double[] x, y; // 波形图数组

	public Code2Wave() {
		String pcodes = JOptionPane.showInputDialog(null, "请输入待发送的信号(自动过滤非数字字符)");

		System.out.println("您输入的信号为"+pcodes);
		codes = new int[pcodes.length()];
		ces = new CodeElement[pcodes.length()];

		for (int i = 0; i < codes.length; i++) {
			if (pcodes.charAt(i) >= '0' && pcodes.charAt(i) <= '9') {
				codes[i] = Integer.parseInt(pcodes.substring(i, i + 1));
			}
			// 对不规范输入的处理
			if (codes[i] > 0)
				codes[i] = 1;
			else
				codes[i] = 0;

			// 绝对码变相对码
			if (i == 0)
				; // 因为参考码为0，所以不需对起始码元做处理
			else {
				if (codes[i] == codes[i - 1]) {
					codes[i] = 0;
				} else {
					codes[i] = 1;
				}
			}
		}

		for (int i = 0; i < codes.length; i++) {
			// 生成起始码元
			if (i == 0) {
				if (codes[0] == 0) {
					ces[0] = new CodeElement(CodeType.START_CODE_ZERO, new double[pcodes.length()]);
				} else {
					ces[0] = new CodeElement(CodeType.START_CODE_ONE, new double[pcodes.length()]);
				}
			} else {
				// rcodes[i]应该要写成CodeType.CODE_ONE/ZERO更规范，
				// 这里这样写是个巧合
				ces[i] = new CodeElement(codes[i], ces[i - 1].getX());
			}
		}

		// 把码元组合起来
		x = new double[pcodes.length() * CodeElement.pointNumberOfPerCode];
		y = new double[pcodes.length() * CodeElement.pointNumberOfPerCode];
		for (int i = 0; i < x.length; i++) {
			int j = i / CodeElement.pointNumberOfPerCode;
			x[i] = ces[j].getX()[i - j * CodeElement.pointNumberOfPerCode];
			y[i] = ces[j].getY()[i - j * CodeElement.pointNumberOfPerCode];
		}

		WaveDrawer.dataViewer(x, y, "a点处波形");
	}

	public double[] getX() {
		return x;
	}

	public double[] getY() {
		return y;
	}

	public int[] getCodes() {
		return codes;
	}
}
