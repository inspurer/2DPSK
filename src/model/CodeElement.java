/**
 * 原创声明:csu_xiaotao@163.com
 */
package model;

/**
 * 新建类声明
 * 
 * @author csu_xiaotao <a href = "https://github.com/inspurer">月小水长</a>
 *         下午2:53:29 2018年7月4日
 */
public class CodeElement {
	
	private double[] x, y;
	
	public static int pointNumberOfPerCode = 1000;

	/**
	 * 
	 * @param codetype 标识码元类型，是相对码，默认参考码是0
	 * @param px   前一个码元的x坐标数组
	 */
	public CodeElement(int codetype, double[] px) {
		x = new double[pointNumberOfPerCode]; // 每2*Pi绘1000点
		y = new double[pointNumberOfPerCode];
		double delta_x = 2 * Math.PI / pointNumberOfPerCode;

		if (codetype == CodeType.START_CODE_ZERO) {
			for (int i = 0; i < x.length; i++) {
				x[i] = delta_x * i;
				y[i] = Math.sin(x[i]);
			}
		}

		else if (codetype == CodeType.START_CODE_ONE) {
			for (int i = 0; i < x.length; i++) {
				x[i] = delta_x * i;
				y[i] = Math.sin(x[i] + Math.PI);
			}
		}
		
		else if(codetype == CodeType.U_CODE_ZERO) {
			for(int i = 0; i<x.length; i++) {
				x[i] = px[i] + Math.PI *2;
				y[i] = Math.sin(x[i]);
			}
		}
		
		else if(codetype == CodeType.U_CODE_ONE) {
			for(int i = 0; i<x.length; i++) {
				x[i] = px[i] + Math.PI *2;
				y[i] = Math.sin(x[i] + Math.PI);
			}
		}
		
	}
	
	public double[] getX() {
		return x;
	}
	
	public double[] getY() {
		return y;
	}
}
