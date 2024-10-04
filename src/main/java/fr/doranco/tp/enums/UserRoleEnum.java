package fr.doranco.tp.enums;

public enum UserRoleEnum {
	
	C ("client"),
	A ("admin"),
	M ("magazinier");
	
	private String value;

	private UserRoleEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
