package constants;

public class Constants {

	public static enum MainFrameEnum {//�� �Ϸ���� �ߴµ�... �ʹ� ���ƞ����� �������� RGB���� ������ָ� ���� �ʹ� �������...
		width("1120"), height("700"),maxWidth("1920"), maxHeight("1080"),//mainPan
		logfindminiX("479"), logfindminiY("623"),singUpminiX("800"), singUpminiY("620"),//minimum size
		contentPanColor("255"),
		mainMark("�����븶ũ.png");//main Mark

		private String value;
		private MainFrameEnum(String value) {this.value = value;}
		public String getString() {return this.value;}
		public int getInt() {return Integer.parseInt(this.value);}
	}
	
	public static final String[] HEADER = {"���¸�","��米��","�ð�","����"};//�⺻ ���.
}
