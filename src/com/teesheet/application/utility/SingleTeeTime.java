package com.teesheet.application.utility;

/**
 * @author Jayden Craft Apr 4, 2022
 */
public class SingleTeeTime {

	private String time;
	private Info info;

	public SingleTeeTime(String time, boolean blocked, String member_name, boolean mem_present, String mem_cart,
			String player1_name, String p1_cart, String p1_reference, String player2_name, String p2_cart,
			String p2_reference, String player3_name, String p3_cart, String p3_reference, String player4_name,
			String p4_cart, String p4_reference, String caddie, String creator) {
		this.time = time;
		this.info = new Info(blocked, member_name, mem_present, mem_cart, player1_name, p1_cart, p1_reference,
				player2_name, p2_cart, p2_reference, player3_name, p3_cart, p3_reference, player4_name, p4_cart,
				p4_reference, caddie, creator);
	}

	public class Info {

		public boolean isBlocked() {
			return blocked;
		}

		public void setBlocked(boolean blocked) {
			this.blocked = blocked;
		}

		public String getMember_name() {
			return member_name;
		}

		public void setMember_name(String member_name) {
			this.member_name = member_name;
		}

		public boolean isMem_present() {
			return mem_present;
		}

		public void setMem_present(boolean mem_present) {
			this.mem_present = mem_present;
		}

		public String getMem_cart() {
			return mem_cart;
		}

		public void setMem_cart(String mem_cart) {
			this.mem_cart = mem_cart;
		}

		public String getPlayer1_name() {
			return player1_name;
		}

		public void setPlayer1_name(String player1_name) {
			this.player1_name = player1_name;
		}

		public String getP1_cart() {
			return p1_cart;
		}

		public void setP1_cart(String p1_cart) {
			this.p1_cart = p1_cart;
		}

		public String getP1_reference() {
			return p1_reference;
		}

		public void setP1_reference(String p1_reference) {
			this.p1_reference = p1_reference;
		}

		public String getPlayer2_name() {
			return player2_name;
		}

		public void setPlayer2_name(String player2_name) {
			this.player2_name = player2_name;
		}

		public String getP2_cart() {
			return p2_cart;
		}

		public void setP2_cart(String p2_cart) {
			this.p2_cart = p2_cart;
		}

		public String getP2_reference() {
			return p2_reference;
		}

		public void setP2_reference(String p2_reference) {
			this.p2_reference = p2_reference;
		}

		public String getPlayer3_name() {
			return player3_name;
		}

		public void setPlayer3_name(String player3_name) {
			this.player3_name = player3_name;
		}

		public String getP3_cart() {
			return p3_cart;
		}

		public void setP3_cart(String p3_cart) {
			this.p3_cart = p3_cart;
		}

		public String getP3_reference() {
			return p3_reference;
		}

		public void setP3_reference(String p3_reference) {
			this.p3_reference = p3_reference;
		}

		public String getPlayer4_name() {
			return player4_name;
		}

		public void setPlayer4_name(String player4_name) {
			this.player4_name = player4_name;
		}

		public String getP4_cart() {
			return p4_cart;
		}

		public void setP4_cart(String p4_cart) {
			this.p4_cart = p4_cart;
		}

		public String getP4_reference() {
			return p4_reference;
		}

		public void setP4_reference(String p4_reference) {
			this.p4_reference = p4_reference;
		}

		public String getCaddie() {
			return caddie;
		}

		public void setCaddie(String caddie) {
			this.caddie = caddie;
		}

		public String getCreator() {
			return creator;
		}

		public void setCreator(String creator) {
			this.creator = creator;
		}

		private boolean blocked;
		private String member_name;
		private boolean mem_present;
		private String mem_cart;
		private String player1_name;
		private String p1_cart;
		private String p1_reference;
		private String player2_name;
		private String p2_cart;
		private String p2_reference;
		private String player3_name;
		private String p3_cart;
		private String p3_reference;
		private String player4_name;
		private String p4_cart;
		private String p4_reference;
		private String caddie;
		private String creator;
		
		@Override
		public String toString() {	
			return  "Member_Name=\"" + "\",mem_present=\"" + mem_present + "\",mem_cart=\"" + mem_cart + "\"," +
	                "Player1_Name=\"" + player1_name + "\", p1_cart=\"" + p1_cart + "\",p1_reference=\"" + p1_reference + "\"," +
	                "Player2_Name=\"" + player2_name + "\", p2_cart=\"" + p2_cart + "\",p2_reference=\"" + p2_reference + "\","+
	                "Player3_Name=\"" + player3_name + "\", p3_cart=\"" + p3_cart + "\",p3_reference=\"" + p3_reference + "\","+
	                "Player4_Name=\"" + player4_name + "\", p4_cart=\"" + p4_cart + "\",p4_reference=\"" + p4_reference + "\","+
	                "Caddie=\"" + caddie + "\",Creator=\"" + creator + "\" WHERE clock=\"" + time + "\"";
		}


		public Info(boolean blocked, String member_name, boolean mem_present, String mem_cart, String player1_name,
				String p1_cart, String p1_reference, String player2_name, String p2_cart, String p2_reference,
				String player3_name, String p3_cart, String p3_reference, String player4_name, String p4_cart,
				String p4_reference, String caddie, String creator) {

			this.blocked = blocked;
			this.member_name = member_name;
			this.mem_present = mem_present;
			this.mem_cart = mem_cart;
			this.player1_name = player1_name;
			this.p1_cart = p1_cart;
			this.p1_reference = p1_reference;
			this.player2_name = player2_name;
			this.p2_cart = p2_cart;
			this.p2_reference = p2_reference;
			this.player3_name = player3_name;
			this.p3_cart = p3_cart;
			this.p3_reference = p3_reference;
			this.player4_name = player4_name;
			this.p4_cart = p4_cart;
			this.p4_reference = p4_reference;
			this.caddie = caddie;
			this.creator = creator;

		}

	}
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	
}
