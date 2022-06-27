package dech.board.post;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.tomcat.jni.Local;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PostService {

	@Autowired
	PostRepository postRepository;

	// Get the highest Id
	public int getHighestPostId() {

		int max = 1;

		List<Post> allUser = postRepository.findAll();
		for (int i = 0; i < allUser.size(); i++) {
			if (allUser.get(i).getPostId() >= 1) {
				max = allUser.get(i).getPostId();
			}

		}

		return max;

	}

	// method to ad a post
	public void addPost(Post post) {
		post.setPostId(getHighestPostId() + 1);
		postRepository.save(post);

	}

	public void addImgToPost(MultipartFile multipartFile, String title) throws IOException, InterruptedException {
		System.out.println("I am in the addImgToPost method!");
		List<Post> allPosts = postRepository.findAll();
		TimeUnit.SECONDS.sleep(1);
		for (Post post : allPosts) {
			System.out.println(post.getTitle() + " = " + title);
			if (post.getTitle().equals(title)) {
				System.out.println("Delete post with id: " + post.getPostId());
				postRepository.deleteById(post.getPostId());
				post.setImage(new Binary(BsonBinarySubType.BINARY, multipartFile.getBytes()));
				System.out.println(post.getImage());
				System.out.println("Save post with id: " + post.getPostId() + " with a image!");
				postRepository.save(post);
			}
		}
	}
	public void checkProfinity(Post post) throws IOException{
		//System.out.println("zzxyzzz");
     //BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\arvan\\Downloads\\Liste.txt"));
	  Path path= Paths.get("test.txt");
		String sPath=path+"";
		BufferedReader br = new BufferedReader(new FileReader(sPath));
      StringBuilder sb = new StringBuilder();
      String line = "";
	  System.out.println("Hi Hello");
      String content= post.getContent();
      String[] contentParts = content.split(" ");
      int check=0;
      while ((line = br.readLine()) != null) {
          System.out.println("1JHUHU");
          for (int i = 0; i < contentParts.length; i++) {
            if (line.equalsIgnoreCase(contentParts[i])) {
				String empty="";
                contentParts [i] = "******";
                check=1;
				
                }
            }

        }
      //System.out.println(contentParts[0]);
      if (check==1) {
          content="";
        for (int i = 0; i < contentParts.length; i++) {
            content = content +contentParts[i]+" ";

        } 
        post.setContent(content);
		
    }
      System.out.println(content);

    }
	public void checkProfinity2 (Post post){
		String content= post.getContent();
		
		String []badWords2 = new String []{"hurensohn", "arschloch ", "penner", "Affenarsch", "Affenkotstück", "Affenmensch", "Allmachtsdackel", "Ameisenficker", "Analbaron", "Analraupe", "Analzone", "anorektische Fettsau", "Armee schwanzloser Primaten", "Arschbratze", "Arschforscher", "Arschfotzengesicht", "Arschgesicht", "Arschhaarbartfratze", "Arschkeks", "Arschkopf", "Arschlocke", "Ashley", "Aso", "Atomspast", "Auspuffbumser", "Bachel", "Badkapp", "bangen", "Berber", "Besen", "Birkenstockfotzenkopf", "Birl", "Blödfisch", "Blödmusiker", "Bodendompteuse", "Brunskuh", "Buckelhur", "Bumsnuss", "Buschmensch", "Butterkuh", "Cholerator", "Computerflüsterer", "Dauerlutscher", "Dollbohrer", "Donnerfotze", "Doofmannsgehilfe", "Doppeldepp", "Dreilochnutte", "Du Ast!", "Dumm-Rasseler", "Dünnschissgurgler", "Eibemme", "Eichelarschkopf", "Fitch", "Flachgeist", "Flattermuschi", "Flodder", "Fontanellenfick", "Fotze", "Fotzklotz", "Gargamel", "Gaylon", "Gebärfrau", "Geburtsfehlermiss", "geistiger Tiefflieger", "Gesichtseintopf", "Gesichtsgünter", "Gesichtsmorph", "Gnarf", "Gnogel", "Grasdackel", "Gratler", "Großhirnkastrat", "H2O-Kopf", "Hack ab!", "Hackfresse", "Halbaffe", "Halma", "Hannefatzke", "hartzig", "Hausschuhgesicht", "HDV", "hirnamputierter Rhinozerusarsch", "Hodenbussard", "Hodenkopf", "Hodenschrubbler", "Hohlbratze", "hohle Fritte", "Honk", "Hosenlottle", "Hosentaschen Godzilla", "HRSN", "Huru", "Hutze", "Hübi", "ins Hirn scheißen", "Intelligenzverweigerer", "Kackbatzen", "Kackstuhl", "Kanisterkopf", "Klapskalli", "Klitzmorchel", "Klufenmichel", "Knallarsch", "Knecht Huso", "Kotlutscher", "Kotzkannenfressbrett", "Kotzkrücke", "Krapfengesicht", "Kuttenluder", "Larve", "Lattenheinrich", "Lattenschreck", "Lällebäbbl", "Liam", "Loser", "mangelbegent", "Missi", "Mongo", "Mr. Grotte", "Muschischeps", "Nafti", "Napfwurst", "Nougatnutte", "Nuddebumber", "Ogerlurch", "Orgasmusbremse", "oshig", "Otzenfrosch", "Paragrafenschubse", "Partyopfer", "Paselacke", "Peniskopf", "Penisprothesenträger", "Pfeifenheini", "Pferdefresse", "Pfingstochse", "pickelgesichtiger Spritzbock", "Pimmel", "Pimmelfresse", "Pimmelkopf", "Pimmelpapagei", "Pisskajole", "Plempe", "Pleppo", "Pottmolch", "Rotzpupsi", "Sackfotze", "Schlampe", "Schlabberlappen", "schleimscheißender Bambusaffe", "Schmalzgrab", "Schmalztitte", "Schmongo", "Schmönkchen", "Schmudel", "schnarchzapfiger Rüsselsack", "Schüttler", "Senfgurke", "Sheamus", "Simbel", "Slut", "Spaltenspengler", "Spammailautor", "Spastard", "spastophil", "Späner", "Spoastie", "Spongebob", "Sponk", "Staubbeutel", "Steroidbeule", "Steroidmutant", "Swarowski-Möse", "Tapetengerippe", "Topmoppel", "Tüffel", "Unwerk", "Vollaffe", "vollgepisste Strumpfhose", "vollgeschissene Menschenhülle", "Vollmongo", "Vongo", "Wachsfresse", "Waldwichtel", "Wichsbazille", "Wichsfisch", "Wikidiot", "Wulfer", "wulffen", "Zahnspangenbettler", "zoologisches Abfallprodukt", "aasgeier", "abspritzer", "sdfds", "ackerfresse", "affenarsch", "affenhirn", "affenkotze", "afterlecker", "aktivex.info", "almosenarsch", "amazing", "am-sperma-riecher", "anal", "analadmiral", "analbesamer", "analbohrer", "analdrill", "analentjungferer", "analerotiker", "analfetischist", "analförster", "anal-frosch", "analnegerdildo", "analratte", "analritter", "aok-chopper", "armleuchter", "arsch", "arschaufreißer", "arschbackenschänder", "arschbesamer", "ärsche", "arschentjungferer", "arschficker", "arschgeburt", "arschgefickte gummifotze", "arschgeige", "arschgesicht", "arschhaarfetischist", "arschhaarrasierer", "arschhöhlenforscher", "arschkrampe", "arschkratzer", "arschlecker", "arschloch", "arschlöcher", "arschmade", "arschratte", "arschzapfen", "arsebandit", "arsehole", "arsejockey", "arselicker", "arsenuts", "arsewipe", "assel", "assfuck", "assfucking", "assgrabber", "asshol", "asshole", "asshole", "assi", "assrammer", "assreamer", "asswipe", "astlochficker", "auspufflutscher", "bad motherfucker", "badass", "badenutte", "bananenstecker", "bastard", "bastard", "bauernschlampe", "beating the meat", "beef curtains", "beef flaps", "behindis", "bekloppter", "muttergeficktes", "beklopter", "bettnässer", "bettpisser", "bettspaltenficker", "biatch", "bimbo", "bitch", "bitches", "bitchnutte", "bitsch", "bizzach", "blechfotze", "blödmann", "blogspoint", "blow job", "bohnenfresser", "boob", "boobes", "boobie", "boobies", "boobs", "booby", "boy love", "breasts", "brechfurz", "bückfleisch", "bückstück", "bückvieh", "buggery", "bullensohn", "bullshit", "bummsen", "bumsen", "bumsklumpen", "buschnutte", "busty", "butt pirate", "buttfuc", "buttfuck", "buttfucker", "buttfucking", "carpet muncher", "carpet munchers", "carpetlicker", "carpetlickers", "chausohn", "clitsuck", "clitsucker", "clitsucking", "cock", "cock sucker", "cockpouch", "cracka", "crap", "craper", "crapers", "crapping", "craps", "cunt", "cunt", "cunts", "dachlattengesicht", "dackelficker", "dickhead", "dicklicker", "diplomarschloch", "doofi", "douglette", "drecksack", "drecksau", "dreckschlitz", "dreckschüppengesicht", "drecksfotze", "drecksmösendagmar", "drecksnigger", "drecksnutte", "dreckspack", "dreckstürke", "dreckvotze", "dumbo", "dummschwätzer", "dumpfbacke", "dünnpfifftrinker", "eichellecker", "eierkopf", "eierlutscher", "eiswürfelpisser", "ejaculate", "entenfisterer", "epilepi", "epilepis", "epileppis", "fagette", "fagitt", "fäkalerotiker", "faltenficker", "fatass", "ferkelficker", "ferkel-ficker", "fettarsch", "fettsack", "fettsau", "feuchtwichser", "fick", "fick*", "fickarsch", "fickdreck", "ficken", "ficker", "fickfehler", "fickfetzen", "fickfresse", "fickfrosch", "fickfucker", "fickgelegenheit", "fickgesicht", "fickmatratze", "ficknudel", "ficksau", "fickschlitz", "fickschnitte", "fickschnitzel", "fingerfuck", "fingerfucking", "fisch-stinkender hodenfresser", "fistfuck", "fistfucking", "flachtitte", "flussfotze", "fotze", "fotzenforscher", "fotzenfresse", "fotzenknecht", "fotzenkruste", "fotzenkuchen", "fotzenlecker", "fotzenlöckchen", "fotzenpisser", "fotzenschmuser", "fotzhobel", "frisösenficker", "frisösenfotze", "fritzfink", "froschfotze", "froschfotzenficker", "froschfotzenleder", "fucked", "fucker", "fucker", "fucking", "fuckup", "fudgepacker", "futtgesicht", "gay lord", "geilriemen", "gesichtsfotze", "göring", "großmaul", "gummifotzenficker", "gummipuppenbumser", "gummisklave", "hackfresse", "hafensau", "hartgeldhure", "heil hitler", "hi hoper", "hinterlader", "hirni", "hitler", "hodenbeißer", "hodensohn", "homo", "hosenpisser", "hosenscheißer", "hühnerficker", "huhrensohn", "hundeficker", "hundesohn", "hurenlecker", "hurenpeter", "hurensohn", "hurentocher", "idiot", "idioten", "itakker", "ittaker", "jack off", "jackass", "jackshit", "jerk off", "jizz", "judensau", "kackarsch", "kacke", "kacken", "kackfass", "kackfresse", "kacknoob", "kaktusficker", "kanacke", "kanake", "kanaken", "kanaldeckelbefruchter", "kartoffelficker", "kinderficken", "kinderficker", "kinderporno", "kitzler fresser", "klapposkop", "klolecker", "klötenlutscher", "knoblauchfresser", "konzentrationslager", "kotgeburt", "kotnascher", "kümmeltürke", "kümmeltürken", "lackaffe", "lebensunwert", "lesbian", "lurchi", "lustbolzen", "lutscher", "magerschwanz", "manwhore", "masturbate", "meat puppet", "missgeburt", "mißgeburt", "mistsau", "miststück", "mitternachtsficker", "mohrenkopf", "mokkastübchenveredler", "mongo", "möse", "mösenficker", "mösenlecker", "mösenputzer", "möter", "mother fucker", "mother fucking", "motherfucker", "muschilecker", "muschischlitz", "mutterficker", "nazi", "nazis", "neger", "nigga", "nigger", "niggerlover", "niggers", "niggerschlampe", "nignog", "nippelsauger", "nutte", "nuttensohn", "nuttenstecher", "nuttentochter", "ochsenpimmel", "ölauge", "oral sex", "penis licker", "penis licking", "penis sucker", "penis sucking", "penis", "peniskopf", "penislecker", "penislutscher", "penissalat", "penner", "pferdearsch", "phentermine", "pimmel", "pimmelkopf", "pimmellutscher", "pimmelpirat", "pimmelprinz", "pimmelschimmel", "pimmelvinni", "pindick", "piss off", "piss", "pissbirne", "pissbotte", "pisse", "pisser", "pissetrinker", "pissfisch", "pissflitsche", "pissnelke", "polacke", "polacken", "poop", "popellfresser", "popostecker", "popunterlage", "porn", "porno", "pornografie", "pornoprengel", "pottsau", "prärieficker", "prick", "quiff", "randsteinwichser", "rasierte votzen", "rimjob", "rindsriemen", "ritzenfummler", "rollbrooden", "roseten putzer", "roseten schlemmer", "rosettenhengst", "rosettenkönig", "rosettenlecker", "rosettentester", "sackfalter", "sackgesicht", "sacklutscher", "sackratte", "saftarsch", "sakfalter", "schamhaarlecker", "schamhaarschädel", "schandmaul", "scheisse", "scheisser", "scheissgesicht", "scheisshaufen", "scheißhaufen", "schlammfotze", "schlampe", "schleimmöse", "schlitzpisser", "schmalspurficker", "schmeue", "schmuckbert", "schnuddelfresser", "schnurbeltatz", "schrumpelfotze", "schwanzlurch", "schwanzlutscher", "schweinepriester", "schweineschwanzlutscher", "schwuchtel", "schwutte", "sex", "shiter", "shiting", "shitlist", "shitomatic", "shits", "shitty", "shlong", "shut the fuckup", "sieg heil", "sitzpisser", "skullfuck", "skullfucker", "skullfucking", "slut", "smegmafresser", "spack", "spacko", "spaghettifresser", "spastard", "spasti", "spastis", "spermafresse", "spermarutsche", "spritzer", "stinkschlitz", "stricher", "suck my cock", "suck my dick", "threesome", "tittenficker", "tittenspritzer", "titties", "titty", "tunte", "untermensch", "vagina", "vergasen", "viagra", "volldepp", "volldeppen", "vollhorst", "vollidiot", "vollpfosten", "vollspack", "vollspacken", "vollspasti", "vorhaut", "votze", "votzenkopf", "wanker", "wankers", "weichei", "whoar", "whore", "wichsbart", "wichsbirne", "wichser", "wichsfrosch", "wichsgriffel", "wichsvorlage", "wickspickel", "wixa", "wixen", "wixer", "wixxer", "wixxxer", "wixxxxer", "wurstsemmelfresser", "yankee", "zappler", "zyclon", "zyklon", "asshole", "assholes", "asshopper", "assjacker", "asslick", "asslicker", "assmonkey", 
		"assmunch", "assmuncher", "asspirate", "assshole", "asssucker", "asswad", "asswhole", 
		"asswipe", "auto erotic", "autoerotic", "b!tch", "b00bs", "b17ch", "b1tch", "babeland", 
		"baby batter", "baby juice", "ball gag", "ball gravy", "ball kicking", "ball licking", 
		"ball sack", "ball sucking", "ballbag", "balls", "ballsack", "bampot", "bangbros", "bareback", 
		"barely legal", "barenaked", "bastard", "bastardo", "bastinado", "bbw", "bdsm", "beaner", "beaners", 
		"beastial", "beastiality", "beastility", "beaver cleaver", "beaver lips", "bellend", "bestial", 
		"bestiality", "bi+ch", "biatch", "big black", "big breasts", "big knockers", "big tits", "bimbos", 
		"birdlock", "bitch", "bitcher", "bitchers", "bitches", "bitchin", "bitching", 
		"black cock", "blonde action", "blonde on blonde action", "bloody", "blow job", "blow your load", 
		"blowjob", "blowjobs", "blue waffle", "blumpkin", "boiolas", "bollock", "bollocks", "bollok", "bollox",
		 "bondage", "boner", "boob", "boobie", "boobs", "booobs", "boooobs", "booooobs", "booooooobs", 
		 "booty call", "breasts", "brown showers", "brunette action", "buceta", "bugger", "bukkake", "bulldyke", 
		 "bullet vibe", "bullshit", "bum", "bung hole", "bunghole", "bunny fucker", "busty", "butt", 
		 "butt-pirate", "buttcheeks", "butthole", "buttmunch", "buttplug", "c0ck", "c0cksucker", "camel toe", "camgirl", "camslut", "camwhore", "carpet muncher", "carpetmuncher", "cawk", "chinc", "chink", "choad", "chocolate rosebuds", "chode", "cipa", "circlejerk", "cl1t", "cleveland steamer", "clit", "clitface", "clitoris", "clits", "clover clamps", "clusterfuck", "cnut", "cock", "cock-sucker", "cockbite", "cockburger", "cockface", "cockhead", "cockjockey", "cockknoker", "cockmaster", "cockmongler", "cockmongruel", "cockmonkey", "cockmunch", "cockmuncher", "cocknose", "cocknugget", "cocks", "cockshit", "cocksmith", "cocksmoker", "cocksuck", "cocksuck ", "cocksucked", "cocksucked ", "cocksucker", "cocksucking", "cocksucks ", "cocksuka", "cocksukka", "cok", "cokmuncher", "coksucka", "coochie", "coochy", "coon", "coons", "cooter", "coprolagnia", "coprophilia", "cornhole", "cox", "crap", "creampie", "cum", "cumbubble", "cumdumpster", "cumguzzler", "cumjockey", "cummer", "cumming", "cums", "cumshot", "cumslut", "cumtart", "cunilingus", "cunillingus", "cunnie", "cunnilingus", "cunt", "cuntface", "cunthole", "cuntlick", "cuntlick ", "cuntlicker", "cuntlicker ", "cuntlicking", "cuntlicking ", "cuntrag", "cunts", "cyalis", "cyberfuc", "cyberfuck ", "cyberfucked ", "cyberfucker", "cyberfuckers", "cyberfucking ", "d1ck", "dammit", "damn", "darkie", "date rape", "daterape", "deep throat", "deepthroat", "dendrophilia", "dick", "dickbag", "dickbeater", "dickface", "dickhead", "dickhole", "dickjuice", "dickmilk", "dickmonger", "dickslap", "dicksucker", "dickwad", "dickweasel", "dickweed", "dickwod", "dike", "dildo", "dildos", "dingleberries", "dingleberry", "dink", "dinks", "dipshit", "dirsa", "dirty pillows", "dirty sanchez", "dlck", "dog style", "dog-fucker", "doggie style", "doggiestyle", "doggin", "dogging", "doggy style", "doggystyle", "dolcett", "domination", "dominatrix", "dommes", "donkey punch", "donkeyribber", "doochbag", "dookie", "doosh", "double dong", "double penetration", "douche", "douchebag", "dp action", "dry hump", "duche", "dumbshit", "dumshit", "dvda", "dyke", "eat my ass", "ecchi", "ejaculate", "ejaculated", "ejaculates ", "ejaculating ", "ejaculatings", "ejaculation", "ejakulate", "erotic", "erotism", "escort", "eunuch", "f u c k", "f u c k e r", "f4nny", "f_u_c_k", "fag", "fagbag", "fagg", "fagging", "faggit", "faggitt", "faggot", "faggs", "fagot", "fagots", "fags", "fagtard", "fanny", "fannyflaps", "fannyfucker", "fanyy", "fart", "farted", "farting", "farty", "fatass", "fcuk", "fcuker", "fcuking", "fecal", "feck", "fecker", "felatio", "felch", "felching", "fellate", "fellatio", "feltch", "female squirting", "femdom", "figging", "fingerbang", "fingerfuck ", "fingerfucked ", "fingerfucker ", "fingerfuckers", "fingerfucking ", "fingerfucks ", "fingering", "fistfuck", "fistfucked ", "fistfucker ", "fistfuckers ", "fistfucking ", "fistfuckings ", "fistfucks ", "fisting", "flamer", "flange", "fook", "fooker", "foot fetish", "footjob", "frotting", "fuck", "fuck buttons", "fucka", "fucked", "fucker", "fuckers", "fuckhead", "fuckheads", "fuckin", "fucking", "fuckings", "fuckingshitmotherfucker", "fuckme ", "fucks", "fucktards", "fuckwhit", "fuckwit", "fudge packer", "fudgepacker", "fuk", "fuker", "fukker", "fukkin", "fuks", "fukwhit", "fukwit", "futanari", "fux", "fux0r", "g-spot", "gang bang", "gangbang", "gangbanged", "gangbanged ", "gangbangs ", "gay sex", "gayass", "gaybob", "gaydo", "gaylord", "gaysex", "gaytard", "gaywad", "genitals", "giant cock", "girl on", "girl on top", "girls gone wild", "goatcx", "goatse", "god damn", "god-dam", "god-damned", "goddamn", "goddamned", "gokkun", "golden shower", "goo girl", "gooch", "goodpoop", "gook", "goregasm", "gringo", "grope", "group sex", "guido", "guro", "hand job", "handjob", "hard core", "hardcore", "hardcoresex ", "heeb", "hell", "hentai", "heshe", "ho", "hoar", "hoare", "hoe", "hoer", "homo", "homoerotic", "honkey", "honky", "hooker", "hore", "horniest", "horny", "hot carl", "hot chick", "hotsex", "how to kill", "how to murder", "huge fat", "humping", "incest", "intercourse", "jack off", "jack-off ", "jackass", "jackoff", "jail bait", "jailbait", "jap", "jelly donut", "jerk off", "jerk-off ", "jigaboo", "jiggaboo", "jiggerboo", "jism", "jiz", "jiz ", "jizm", "jizm ", "jizz", "juggs", "kawk", "kike", "kinbaku", "kinkster", "kinky", "kiunt", "knob", "knobbing", "knobead", "knobed", "knobend", "knobhead", "knobjocky", "knobjokey", "kock", "kondum", "kondums", "kooch", "kootch", "kum", "kumer", "kummer", "kumming", "kums", "kunilingus", "kunt", "kyke", "l3i+ch", "l3itch", "labia", "leather restraint", "leather straight jacket", "lemon party", "lesbo", "lezzie", "lmfao", "lolita", "lovemaking", "lust", "lusting", "m0f0", "m0fo", "m45terbate", "ma5terb8", "ma5terbate", "make me come", "male squirting", "masochist", "master-bate", "masterb8", "masterbat*", "masterbat3", "masterbate", "masterbation", "masterbations", "masturbate", "menage a trois", "milf", "minge", "missionary position", "mo-fo", "mof0", "mofo", "mothafuck", "mothafucka", "mothafuckas", "mothafuckaz", "mothafucked ", "mothafucker", "mothafuckers", "mothafuckin", "mothafucking ", "mothafuckings", "mothafucks", "mother fucker", "motherfuck", "motherfucked", "motherfucker", "motherfuckers", "motherfuckin", "motherfucking", "motherfuckings", "motherfuckka", "motherfucks", "mound of venus", "mr hands", "muff", "muff diver", "muffdiver", "muffdiving", "mutha", "muthafecker", "muthafuckker", "muther", "mutherfucker", "n1gga", "n1gger", "nambla", "nawashi", "nazi", "negro", "neonazi", "nig nog", "nigg3r", "nigg4h", "nigga", "niggah", "niggas", "niggaz", "nigger", "niggers ", "niglet", "nimphomania", "nipple", "nipples", "nob", "nob jokey", "nobhead", "nobjocky", "nobjokey", "nsfw images", "nude", "nudity", "numbnuts", "nutsack", "nympho", "nymphomania", "octopussy", "omorashi", "one cup two girls", "one guy one jar", "orgasim", "orgasim ", "orgasims ", "orgasm", "orgasms ", "orgy", "p0rn", "paedophile", "paki", "panooch", "panties", "panty", "pawn", "pecker", "peckerhead", "pedobear", "pedophile", "pegging", "penis", "penisfucker", "phone sex", "phonesex", "phuck", "phuk", "phuked", "phuking", "phukked", "phukking", "phuks", "phuq", "piece of shit", "pigfucker", "pimpis", "pis", "pises", "pisin", "pising", "pisof", "piss", "piss pig", "pissed", "pisser", "pissers", "pisses ", "pissflap", "pissflaps", "pissin", "pissin ", "pissing", "pissoff", "pissoff ", "pisspig", "playboy", "pleasure chest", "pole smoker", "polesmoker", "pollock", "ponyplay", "poo", "poof", "poon", "poonani", "poonany", "poontang", "poop", "poop chute", "poopchute", "porn", "porno", "pornography", "pornos", "prick", "pricks ", "prince albert piercing", "pron", "pthc", "pube", "pubes", "punanny", "punany", "punta", "pusies", "pusse", "pussi", "pussies", "pussy", "pussylicking", "pussys ", "pusy", "puto", "queaf", "queef", "queerbait", "queerhole", "quim", "raghead", "raging boner", "rape", "raping", "rapist", "rectum", "renob", "retard", "reverse cowgirl", "rimjaw", "rimjob", "rimming", "rosy palm", "rosy palm and her 5 sisters", "ruski", "rusty trombone", "s hit", "s&m", "s.o.b.", "s_h_i_t", "sadism", "sadist", "santorum", "scat", "schlong", "scissoring", "screwing", "scroat", "scrote", "scrotum", "semen", "sex", "sexo", "sexy", "sh!+", "sh!t", "sh1t", "shag", "shagger", "shaggin", "shagging", "shaved beaver", "shaved pussy", "shemale", "shi+", "shibari", "shit", "shit-ass", "shit-bag", "shit-bagger", "shit-brain", "shit-breath", "shit-cunt", "shit-dick", "shit-eating", "shit-face", "shit-faced", "shit-fit", "shit-head", "shit-heel", "shit-hole", "shit-house", "shit-load", "shit-pot", "shit-spitter", "shit-stain", "shitass", "shitbag", "shitbagger", "shitblimp", "shitbrain", "shitbreath", "shitcunt", "shitdick", "shite", "shiteating", "shited", "shitey", "shitface", "shitfaced", "shitfit", "shitfuck", "shitfull", "shithead", "shitheel", "shithole", "shithouse", "shiting", "shitings", "shitload", "shitpot", "shits", "shitspitter", "shitstain", "shitted", "shitter", "shitters ", "shittiest", "shitting", "shittings", "shitty", "shitty ", "shity", "shiz", "shiznit", "shota", "shrimping", "skank", "skeet", "slanteye", "slut", "slutbag", "sluts", "smeg", "smegma", "smut", "snatch", "snowballing", "sodomize", "sodomy", "son-of-a-bitch", "spac", "spic", "spick", "splooge", "splooge moose", "spooge", "spread legs", "spunk", "strap on", "strapon", "strappado", "strip club", "style doggy", "suck", "sucks", "suicide girls", "sultry women", "swastika", "swinger", "t1tt1e5", "t1tties", "tainted love", "tard", "taste my", "tea bagging", "teets", "teez", "testical", "testicle", "threesome", "throating", "thundercunt", "tied up", "tight white", "tit", "titfuck", "tits", "titt", "tittie5", "tittiefucker", "titties", "titty", "tittyfuck", "tittywank", "titwank", "tongue in a", "topless", "tosser", "towelhead", "tranny", "tribadism", "tub girl", "tubgirl", "turd", "tushy", "tw4t", "twat", "twathead", "twatlips", "twatty", "twink", "twinkie", "two girls one cup", "twunt", "twunter", "undressing", "upskirt", "urethra play", "urophilia", "v14gra", "v1gra", "va-j-j", "vag", "vagina", "venus mound", "viagra", "vibrator", "violet wand", "vjayjay", "vorarephilia", "voyeur", "vulva", "w00se", "wang", "wank", "wanker", "ahole", "anus", "ash0le", "ash0les", "asholes", "ass", "Ass Monkey", "Assface", "assh0le", "assh0lez", "asshole", "assholes", "assholz", "asswipe", "azzhole", "bassterds", "bastard", "bastards", "bastardz", "basterds", "basterdz", "Biatch", "bitch", "bitches", "Blow Job", "boffing", "butthole", "buttwipe", "c0ck", "c0cks", "c0k", "Carpet Muncher", "cawk", "cawks", "Clit", "cnts", "cntz", "cock", "cockhead", "cock-head", "cocks", "CockSucker", "cock-sucker", "crap", "cum", "cunt", "cunts", "cuntz", "dick", "dild0", "dild0s", "dildo", "dildos", "dilld0", "dilld0s", "dominatricks", "dominatrics", "dominatrix", "dyke", "enema", "f u c k", "f u c k e r" 
		 , "knobz", "kunt", "kunts", "kuntz", "Lesbian", "Lezzian", "Lipshits", "Lipshitz", "masochist", "masokist", "massterbait", "masstrbait", "masstrbate", "masterbaiter", "masterbate", "masterbates", "Motha Fucker", "Motha Fuker", "Motha Fukkah", "Motha Fukker", "Mother Fucker", "Mother Fukah", "Mother Fuker", "Mother Fukkah", "Mother Fukker", "mother-fucker", "Mutha Fucker", "Mutha Fukah", "Mutha Fuker", "Mutha Fukkah", "Mutha Fukker", "n1gr", "nastt", "nigger;", "nigur;", "niiger;", "niigr;", "orafis", "orgasim;", "orgasm", "orgasum", "oriface", "orifice", "orifiss", "packi", "packie", "packy", "paki", "pakie", "paky", "pecker", "peeenus", "peeenusss", "peenus", "peinus", "pen1s", "penas", "penis", "penis-breath", "penus", "penuus", "Phuc", "Phuck", "Phuk", "Phuker", "Phukker", "polac", "polack", "polak", "Poonani", "pr1c", "pr1ck", "pr1k", "pusse", "pussee", "pussy", "puuke", "puuker", "queer", "queers", "queerz", "qweers", "qweerz", "qweir", "recktum", "rectum", "retard", "sadist", "scank", "schlong", "screwing", "semen", "sex", "sexy", "Sh!t", "sh1t", "sh1ter", "sh1ts", "sh1tter", "sh1tz", "shit", "shits", "shitter", "Shitty", "Shity", "shitz", "Shyt", "Shyte", "Shytty", "Shyty", "skanck", "skank", "skankee", "skankey", "skanks", "Skanky", "slut", "sluts", "Slutty", "slutz", "son-of-a-bitch", "tit", "turd", "va1jina", "vag1na", "vagiina", "vagina", "vaj1na", "vajina", "vullva", "vulva", "w0p", "wh00r", "wh0re", "whore", "xrated", "xxx", "b!+ch", "bitch", "blowjob", "clit", "arschloch", "fuck", "shit", "ass", "asshole", "b!tch", "b17ch", "b1tch", "bastard", "bi+ch", "boiolas", "buceta", "c0ck", "cawk", "chink", "cipa", "clits", "cock", "cum", "cunt", "dildo", "dirsa", "ejakulate", "fatass", "fcuk", "fuk", "fux0r", "hoer", "hore", "jism", "kawk", "l3itch", "l3i+ch", "lesbian", "masturbate", "masterbat*", "masterbat3", "motherfucker", "s.o.b.", "mofo", "nazi", "nigga", "nigger", "nutsack", "phuck", "pimpis", "pusse", "pussy", "scrotum", "sh!t", "shemale", "shi+", "sh!+", "slut", "smut", "teets", "tits", "boobs", "b00bs", "teez", "testical", "testicle", "titt", "w00se", "jackoff", "wank", "whoar", "whore", "*damn", "*dyke", "*fuck*", "*shit*", "@$$", "amcik", "andskota", "arse*", "assrammer", "ayir", "bi7ch", "bitch*", "bollock*", "breasts", "butt-pirate", "cabron", "cazzo", "chraa", "chuj", "Cock*", "cunt*", "d4mn", "daygo", "dego", "dick*", "dike*", "dupa", "dziwka", "ejackulate", "Ekrem*", "Ekto", "enculer", "faen", "fag*", "fanculo", "fanny", "feces", "feg", "Felcher", "ficken", "fitt*", "Flikker", "foreskin", "Fotze", "Fu(*", "fuk*", "futkretzn", "gay", "gook", "guiena", "h0r", "h4x0r", "hell", "helvete", "hoer*", "honkey", "Huevon", "hui", "injun", "jizz", "kanker*", "kike", "klootzak", "kraut", "knulle", "kuk", "kuksuger", "Kurac", "kurwa", "kusi*", "kyrpa*", "lesbo", "mamhoon", "masturbat*", "merd*", "mibun", "monkleigh", "mouliewop", "muie", "mulkku", "muschi", "nazis", "nepesaurio", "nigger*", "orospu", "paska*", "perse", "picka", "pierdol*", "pillu*", "pimmel", "piss*", "pizda", "poontsee", "poop", "porn", "p0rn", "pr0n", "preteen", "pula", "pule", "puta", "puto", "qahbeh", "queef*", "rautenberg", "schaffer", "scheiss*", "schlampe", "schmuck", "screw", "sh!t*", "sharmuta", "sharmute", "shipal", "shiz", "skribz", "skurwysyn", "sphencter", "spic", "spierdalaj", "splooge", "suka", "b00b*", "testicle*", "titt*", "twat", "vittu", "wank*", "wetback*", "wichser", "wop*", "yed", "zabourah"};
		
		ArrayList<String> badWords= new ArrayList<>();
		for(int i =0; i<badWords2.length;i++){
			badWords.add(badWords2[i]);
		}
		for(int i =0; i<badWords.size(); i++){

			if(content.contains(badWords.get(i))){
				//badWord=badWords.get(i);
				content=content.replace(badWords.get(i),"***** " );
				post.setContent(content);
			
		}
		}
	}
	public Binary getImagebyTitle(String title) {
		List<Post> allPosts = postRepository.findAll();

		for (Post post : allPosts) {
			System.out.println(post.getTitle() + " = " + title);
			if (post.getTitle().equals(title)) {
				return post.getImage();
			}
		}
		return null;
	}

	public List<Post> getPosts(int boardId) {

		List<Post> allPosts = postRepository.findAll();

		System.out.println(boardId);

		List<Post> postsOfBoard = new ArrayList<>();

		for (Post post : allPosts) {
			//System.out.println("---------------------------------------------------------");
			//System.out.println(post);
			if (post.getMessageBoardId() == boardId) {

				postsOfBoard.add(post);
				//System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
				//System.out.println(post);
			}
		}

		return sortPosts(postsOfBoard);

	}

	public List<Post> sortPosts(List<Post> allPosts) {

		Collections.sort(allPosts, new Comparator<Post>() {
			public int compare(Post post1, Post post2) {
				return String.valueOf(post1.getCreationDate().getDayOfYear())
						.compareTo(String.valueOf(post2.getCreationDate().getDayOfYear()));
			}
		});

		Collections.reverse(allPosts);
		List<Post> sendPost= new ArrayList<>();
		//List<Post> sortThePosts = allPosts;
	// 	if(allPosts.size()>0){
		

	// 	List<LocalDateTime> dates = new ArrayList<>();
	// 	dates.add(allPosts.get(0).getCreationDate());
	// 	System.out.println("Hallo "+allPosts.get(0).getCreationDate());
	// 	System.out.println("Hallo2 "+dates);
	// 	for(int i = 0; i<dates.size(); i++){
	// 	for(Post post : allPosts){
			
	// 		if(dates.get(i).getDayOfYear() !=post.getCreationDate().getDayOfYear()){
	// 			dates.add(post.getCreationDate());
	// 		}	
			
	// 	}
	// 	}
	// 	List<Post> impPost = new ArrayList<>();
	// 	List<Post> normalPost = new ArrayList<>();

	// 	for(LocalDateTime time : dates){
	// 	for(Post post : allPosts ){
	// 		//  if(post.getImportant()==null){
	// 		// 	post.setImportant(false);
	// 		//  }

	// 			if(post.getCreationDate() == time && post.getImportant()!=null){
	// 				impPost.add(post);
					
	// 			}
	// 			else{
	// 				normalPost.add(post);
	// 			}

	// 		 }
			 
			 
	// 		 sendPost.addAll(impPost);
	// 		 sendPost.addAll(normalPost);
	// 		 impPost.clear();
	// 		 normalPost.clear();

	// 	}


	// 	//return allPosts;
	// }
		//return sendPost;
		return allPosts;
	}

}
