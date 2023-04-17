{ pkgs }: {
	deps = [
		pkgs.openjdk8
		pkgs.maven
		pkgs.replitPackages.jdt-language-server
		pkgs.replitPackages.java-debug
	];
}