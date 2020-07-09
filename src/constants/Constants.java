package constants;

public class Constants {

	public static enum MainFrameEnum {//다 하려고는 했는데... 너무 많아옄ㅋㅋ 색같은건 RGB따로 만들어주면 줄이 너무 길어지고...
		width("1120"), height("700"),maxWidth("1920"), maxHeight("1080"),//mainPan
		logfindminiX("479"), logfindminiY("623"),singUpminiX("800"), singUpminiY("620"),//minimum size
		contentPanColor("255"),
		mainMark("명지대마크.png");//main Mark

		private String value;
		private MainFrameEnum(String value) {this.value = value;}
		public String getString() {return this.value;}
		public int getInt() {return Integer.parseInt(this.value);}
	}
	
	public static final String[] HEADER = {"강좌명","담당교수","시간","학점"};//기본 헤드.
}
