package dropoutinnovations.soundlogoquiz;


public class Sound {


	public static int[] soundArray = {
			R.raw.nokia, R.raw.harrypotter, R.raw.iphone, R.raw.skype, R.raw.yahoo,
			R.raw.xp, R.raw.twitter, R.raw.centuryfox, R.raw.mcd, R.raw.xbox, R.raw.pixar, R.raw.mac,
			R.raw.nickelodeon, R.raw.vevo, R.raw.abc, R.raw.psp, R.raw.philips, R.raw.warnerbros,
			R.raw.columbiapictures, R.raw.tmobile, R.raw.samsung, R.raw.sonypicturestelivision,
			R.raw.showtime, R.raw.pillsbury, R.raw.mgm, R.raw.thx, R.raw.oldspice,
			R.raw.nationalgeographic, R.raw.mtv, R.raw.coke, R.raw.windows7, R.raw.att,
			R.raw.nbc, R.raw.bbc, R.raw.dreamworks, R.raw.mercedesbenz, R.raw.intel,
			R.raw.audi, R.raw.universaltelivision, R.raw.lg, R.raw.universal, R.raw.espn,
			R.raw.farmersinsurance, R.raw.waltdisneypictures, R.raw.lufthansa, R.raw.cnn,
			R.raw.hbo, R.raw.duracell
	};

	public static String[] answerArray = {
			"nokia", "harry potter", "iphone", "skype", "yahoo",
			"windows xp", "twitter", "20th century fox", "mcdonald's", "xbox", "pixar", "macintosh",
			"nickelodeon", "vevo", "abc", "play station portable", "philips", "warner bros entertainment",
			"columbia pictures", "t-mobile", "samsung", "sony pictures telivision",
			"showtime", "pillsbury", "metro-goldwyn-mayer", "thx", "old spice",
			"national geographic channel", "mtv", "coca-cola", "windows 7", "at&t",
			"nbc", "bbc", "dreamworks studios", "mercedes-benz", "intel",
			"audi", "universal telivision", "lg", "universal pictures", "espn",
			"farmers insurance", "walt disney pictures", "lufthansa", "cnn",
			"hbo", "duracell"
	};

	public static String[] correctAnswerArray = {
			"nokia", "harrypotter", "iphone", "skype", "yahoo",
			"windowsxp", "twitter", "20thcenturyfox", "mcdonalds", "xbox", "pixar", "macintosh",
			"nickelodeon", "vevo", "abc", "playstationportable", "philips", "warnerbrosentertainment",
			"columbiapictures", "tmobile", "samsung", "sonypicturestelivision",
			"showtime", "pillsbury", "metrogoldwynmayer", "thx", "oldspice",
			"nationalgeographicchannel", "mtv", "cocacola", "windows7", "att",
			"nbc", "bbc", "dreamworksstudios", "mercedesbenz", "intel",
			"audi", "universaltelivision", "lg", "universalpictures", "espn",
			"farmersinsurance", "waltdisneypictures", "lufthansa", "cnn",
			"hbo", "duracell"
	};

	public static String[] soundHint = {

			"Finnish company famous for producing phones",
			"7 Books. 8 Movies. 1 Story. ",
			"Smartphone made by Apple",
			"This application is synonymous with video calling",
			"This website's CEO is Marissa Mayer",

			"Operating system released by windows in the early 2000's ",
			"#SocialMedia",
			"This film studio is named in the previous century",
			"The world's largest chain of hamburger fast food restaurants",
			"Gaming console from Microsoft",
			"Computer animation film studio that produced Toy Story",
			"Personal computers designed by Apple",

			"Most of this cable channel's programming is aimed at children and adolescents",
			"Popular service for hosting music videos on YouTube",
			"Lost and Modern Family are some of the popular shows on this network ",
			"Handheld gaming device from Sony ",
			"Dutch company popular for lighting",
			"A major film studio and a subsidiary of Time Warner",

			"Did you ever see a woman carrying a torch and draped in the American flag? ",
			"The fourth largest wireless network in the U.S. ",
			"A South Korean multinational conglomerate company",
			"American television production studio which is a part of Sony",

			"This TV network is occasionally abbreviated as SHO",
			"The Doughboy is the mascot of this company",
			"Remember Tom and Jerry? This studio made it",
			"Audio/visual reproduction standard for movie theaters",
			"A prominent American brand of male grooming products",

			"This channel features documentaries involving nature, science, culture, and history",
			"The original purpose of the channel was to play music videos guided by VJs",
			"The most valuable and recognized soft drink brand in the world",
			"This operating system's codename was \"Blackcomb.\" ",
			"The second largest provider of mobile telephone and the largest provider of fixed telephone in the U.S.",

			"This is the peacock network",
			"The world's oldest national broadcasting organization",
			"American animation studio which made Shrek, Madagascar and Kung Fu Panda",
			"German brand used for luxury automobiles, buses, coaches, and trucks",
			"This company makes i series processors",

			"German automobile manufacturer with four rings in its logo. ",
			"Television production arm of the NBCUniversal",
			"Life's good",
			"American film studio owned by Comcast",
			"This network�s signature telecast is the Sportscenter",

			"JK Simmons is featured as Professor Nathaniel Burke of the University of Farmers in TV ads",
			"Some well-known releases of this studio include the Pirates of the Caribbean series and Mary Poppins",
			"The largest airline in Europe",
			"This news network's famous tagline is \"This is ...\" ",

			"The seven kingdoms fight for the iron throne on this network",
			"An American brand product line of batteries and smart power systems"


	};

	public static boolean isThisCorrect(String userEntry, int soundNo) {
		String newString = convert(userEntry);
		return doesThisMatch(newString, soundNo);

	}

	public static String convert(String string) {
		string.replaceAll("\\s+", "");
		string = string.toLowerCase();
		StringBuilder sbuilder = new StringBuilder(string);

		for (int i = 0; i < sbuilder.length(); i++) {
			if (sbuilder.charAt(i) < '0') {
				sbuilder.deleteCharAt(i);
			} else if (sbuilder.charAt(i) > '9' && sbuilder.charAt(i) < 'a') {
				sbuilder.deleteCharAt(i);
			} else if (sbuilder.charAt(i) > 'z') {
				sbuilder.deleteCharAt(i);
			}
		}
		return sbuilder.toString();
	}

	public static boolean doesThisMatch(String string, int num) {
		if (num == 29) {
			return string.equals("coke") || string.equals("cocacola");

		} else if (num == 18) {
			return string.equals("columbia") || string.equals("columbiapictures");

		} else if (num == 31) {
			return string.equals("att") || string.equals("atandt") || string.equals("at&t");

		} else if (num == 12) {
			return string.equals("nickelodeon") || string.equals("nick");

		} else if (num == 5) {
			return string.equals("windows") || string.equals("xp") || string.equals("windowsxp");

		} else if (num == 30) {
			return string.equals("windows") || string.equals("windows7");

		} else if (num == 38) {
			return string.equals("universaltelivision") || string.equals("universal");

		} else if (num == 40) {
			return string.equals("universalpictures") || string.equals("universal");

		} else if (num == 42) {
			return string.equals("farmers") || string.equals("farmersinsurance");

		} else if (num == 43) {
			return string.equals("waltdisneypictures") || string.equals("disney") || string.equals("waltdisney");

		} else if (num == 24) {
			return string.equals("metrogoldwynmayer") || string.equals("mgm") || string.equals("metrogoldwynmayerstudios");

		} else if (num == 27) {
			return string.equals("nationalgeographicchannel") || string.equals("natgeo") || string.equals("nationalgeographic");

		} else if (num == 35) {
			return string.equals("mercedesbenz") || string.equals("benz") || string.equals("mercedes");

		} else if (num == 17) {
			return string.equals("warnerbrosentertainment") || string.equals("warnerbrothersentertainment") || string.equals("warnerbros") || string.equals("warnerbrothers") || string.equals("wb");
		} else if (num == 21) {
			return string.equals("sonypicturestelivision") || string.equals("sonypictures") || string.equals("sonytelivision");
		} else if (num == 34) {
			return string.equals("dreamworksstudios") || string.equals("dreamworks") || string.equals("dreamworkspictures") || string.equals("dreamworksskg") || string.equals("dreamworksanimationskg") || string.equals("dreamworksanimation");
		} else if (num == 11) {
			return string.equals("mac") || string.equals("macintosh");
		} else if (num == 15) {
			return string.equals("psp") || string.equals("playstationportable") || string.equals("playstation");
		} else {
			return string.equals(correctAnswerArray[num]);
		}
	}
}