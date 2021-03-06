package levy.daniel.application.metier.services.enregistreursfichiers;

import java.io.File;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * Interface IEnregistreurFichiers :<br/>
 * Interface centralisant les méthodes de toutes les classes 
 * qui fournissent des services d'enregistrement de fichiers sur disque 
 * et qui produisent un rapport d'enregistrement.<br/>
 * SERVICE CHARGE D'ENREGISTRER UNE STRING SUR DISQUE.<br/>
 * <br/>
 * <ul>
 * comporte essentiellement : <br/>
 * <li>une méthode ecrireStringDansFile(
 * File pFile, String pString, Charset pCharset, String pSautLigne) 
 * qui permet d'enregistrer pString encodée en pCharset 
 * dans pFile sur disque.<br/>
 * Cette méthode peut substituer pSautLigne aux sauts 
 * de ligne rencontrés dans pString.<br/>
 * Cette méthode doit générer un rapport d'enregistrement 
 * du fichier sous forme de List&lt;LigneRapportEnregistrement&gt;.</li><br/>
 * </ul>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * Enregistrement de fichier sur disque, écriture sur disque<br/>
 * ecriture sur disque, saut de ligne<br/>
 * sauts de ligne de la plateforme (NEWLINE),<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * levy.daniel.application.IExportateurCsv 
 * (sous-entendu, pas d'implémentation directe).<br/>
 * levy.daniel.application.IExportateurJTable 
 * (sous-entendu, pas d'implémentation directe).<br/>
 * levy.daniel.application.IResetable 
 * (sous-entendu, pas d'implémentation directe).<br/>
 * levy.daniel.application.metier.services.enregistreursfichiers.rapportsenregistrements.LigneRapportEnregistrement.<br/>
 * levy.daniel.application.metier.services.enregistreursfichiers.IRapporteurEnregistrement.<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 3 mars 2016
 *
 */
public interface IEnregistreurFichiers extends IRapporteurEnregistrement {
	
		
	/**
	 * CHARSET_UTF8 : Charset :<br/>
	 * Charset.forName("UTF-8").<br/>
	 * Eight-bit Unicode (or UCS) Transformation Format.<br/> 
	 */
	Charset CHARSET_UTF8 
		= Charset.forName("UTF-8");

	
	/**
	 * CHARSET_US_ASCII : Charset :<br/>
	 * Charset.forName("US-ASCII").<br/>
	 * Seven-bit ASCII, a.k.a. ISO646-US, 
	 * a.k.a. the Basic Latin block of the Unicode character set.<br/>
	 * standard américain.<br/>
	 * American Standard Code for Information Interchange.<br/> 
	 * 128 caractères imprimables.<br/> 
	 * Sert à écrire l’anglo-américain.<br/> 
	 * Ne permet pas d’écrire les langues européennes 
	 * qui utilisent des lettres avec diacritiques (accents, cédille, ...).<br/> 
	 * On ne peut pas écrire en français avec de l’ASCII.<br/>
	 */
	Charset CHARSET_US_ASCII 
		= Charset.forName("US-ASCII");

	
	/**
	 * CHARSET_ISO_8859_1 : Charset :<br/>
	 * Charset.forName("ISO-8859-1").<br/>
	 * Latin1.<br/>
	 * 191 caractères imprimables.<br/> 
	 * Permet d’écrire la plupart des langues d’Europe de l’Ouest.<br/> 
	 * Presque tous les caractères du français y sont (manquent le œ et €).<br/>
	 */
	Charset CHARSET_ISO_8859_1 
		= Charset.forName("ISO-8859-1");

	
	/**
	 * CHARSET_LATIN1 : Charset :<br/>
	 * Charset.forName("ISO-8859-1").<br/>
	 * Latin1.<br/>
	 * 191 caractères imprimables.<br/> 
	 * Permet d’écrire la plupart des langues d’Europe de l’Ouest.<br/> 
	 * Presque tous les caractères du français y sont (manquent le œ et €).<br/>
	 */
	Charset CHARSET_LATIN1 
		= Charset.forName("ISO-8859-1");

	
	/**
	 * CHARSET_ISO_8859_2 : Charset :<br/>
	 * Charset.forName("ISO-8859-2").<br/>
	 * <br/>
	 */
	Charset CHARSET_ISO_8859_2 
		= Charset.forName("ISO-8859-2");
	
	
	/**
	 * CHARSET_ISO_8859_9 : Charset :<br/>
	 * Charset.forName("ISO-8859-9").<br/>
	 * Latin Alphabet No. 5<br/>
	 */
	Charset CHARSET_ISO_8859_9 
		= Charset.forName("ISO-8859-9");

	
	/**
	 * CHARSET_ISO_8859_15 : Charset :<br/>
	 * Charset.forName("ISO-8859-15").<br/>
	 * Latin9, Latin Alphabet No. 9.<br/>
	 * modifie légèrement ISO-8859-1.<br/> 
	 * Ajout du caractère œ et du symbole monétaire € (Euro) entre autres.<br/>
	 */
	Charset CHARSET_ISO_8859_15 
		= Charset.forName("ISO-8859-15");
	
	
	/**
	 * CHARSET_LATIN9 : Charset :<br/>
	 * Charset.forName("ISO-8859-15").<br/>
	 * Latin9, Latin Alphabet No. 9.<br/>
	 * modifie légèrement ISO-8859-1.<br/> 
	 * Ajout du caractère œ et du symbole monétaire € (Euro) entre autres.<br/>
	 */
	Charset CHARSET_LATIN9 
		= Charset.forName("ISO-8859-15");
	
	
	/**
	 * CHARSET_WINDOWS_1252 : Charset :<br/>
	 * Charset.forName("windows-1252").<br/>
	 * ANSI, CP1252.<br/>
	 * 218 caractères imprimables.<br/>
	 * extension d’ISO-8859-1, qui rajoute quelques caractères: œ, € (euro), 
	 * guillemets anglais (« »), points de suspension (...)
	 * , signe «pour mille» (‰), 
	 * tirets cadratin (— = \u2014 en unicode ) et demi-cadratin (–), ...<br/>
	 */
	Charset CHARSET_WINDOWS_1252 
		= Charset.forName("windows-1252");

	
	/**
	 * CHARSET_ANSI : Charset :<br/>
	 * Charset.forName("windows-1252").<br/>
	 * ANSI, CP1252.<br/>
	 * 218 caractères imprimables.<br/>
	 * extension d’ISO-8859-1, qui rajoute quelques caractères: œ, € (euro), 
	 * guillemets anglais (« »), points de suspension (...)
	 * , signe «pour mille» (‰), 
	 * tirets cadratin (— = \u2014 en unicode ) et demi-cadratin (–), ...<br/>
	 */
	Charset CHARSET_ANSI
		= Charset.forName("windows-1252");

	
	/**
	 * CHARSET_CP1252 : Charset :<br/>
	 * Charset.forName("windows-1252").<br/>
	 * ANSI, CP1252.<br/>
	 * 218 caractères imprimables.<br/>
	 * extension d’ISO-8859-1, qui rajoute quelques caractères: œ, € (euro), 
	 * guillemets anglais (« »), points de suspension (...)
	 * , signe «pour mille» (‰), 
	 * tirets cadratin (— = \u2014 en unicode ) et demi-cadratin (–), ...<br/>
	 */
	Charset CHARSET_CP1252
		= Charset.forName("windows-1252");

	
	/**
	 * CHARSET_IBM850 : Charset :<br/>
	 * Charset IBM-850.<br/>
	 * Cp850, MS-DOS Latin-1.<br/>
	 */
	Charset CHARSET_IBM850
		= Charset.forName("IBM-850");
	

	
	/**
	 * SAUTDELIGNE_UNIX : String :<br/>
	 * Saut de ligne généré par les éditeurs Unix.<br/>
	 * "\n" (Retour Ligne = LINE FEED (LF)).
	 */
	String SAUTDELIGNE_UNIX = "\n";

	
	/**
	 * SAUTDELIGNE_MAC : String :<br/>
	 * Saut de ligne généré par les éditeurs Mac.<br/>
	 * "\r" (Retour Chariot RC = CARRIAGE RETURN (CR))
	 */
	String SAUTDELIGNE_MAC = "\r";

	
	/**
	 * SAUTDELIGNE_DOS_WINDOWS : String :<br/>
	 * Saut de ligne généré par les éditeurs DOS/Windows.<br/>
	 * "\r\n" (Retour Chariot RC + Retour Ligne LF).
	 */
	String SAUTDELIGNE_DOS_WINDOWS = "\r\n";

		
	/**
	 * NEWLINE : String :<br/>
	 * Saut de ligne spécifique de la plateforme.<br/>
	 * System.getProperty("line.separator").<br/>
	 */
	String NEWLINE = System.getProperty("line.separator");

	
	/**
	 * UNDERSCORE : char :<br/>
	 * '_'.<br/>
	 */
	char UNDERSCORE = '_';
	
	
	/**
	 * POINT : char :<br/>
	 * '.'.<br/>
	 */
	char POINT = '.';
	
	
	/**
	 * SEPARATEUR_FILE : String :<br/>
	 * "\\".<br/>
	 */
	String SEPARATEUR_FILE = "\\";
	

	/**
	 * SEP_POINTVIRGULE : String :<br/>
	 * ";".<br/>
	 */
	String SEP_POINTVIRGULE = ";";

	
	/**
	 * SEP_MOINS : String :<br/>
	 * " - ".<br/>
	 */
	String SEP_MOINS = " - ";

	
	/**
	 * SEP_REP : String :<br/>
	 * Séparateur Java pour les répertoires "\\".<br/>
	 */
	String SEP_REP = "\\";
	
	
	/**
	 * LOCALE_FR_FR : Locale :<br/>
	 * new Locale("fr", "FR").<br/>
	 * "fr" correspond au langage et "FR" au pays.<br/>
	 */
	Locale LOCALE_FR_FR = new Locale("fr", "FR");

	
	/**
	 * LOCALE_SYSTEM : Locale :<br/>
	 * Locale de la plateforme.<br/>
	 * Locale.getDefault().<br/>
	 */
	Locale LOCALE_SYSTEM = Locale.getDefault();
	

	/**
	 * DF_DATE_LEXICOGRAPHIQUE : DateFormat :<br/>
	 * Format lexicographique des dates 
	 * comme "2012-01-16" pour le
	 * 16 Janvier 2012.<br/>
	 * "yyyy-MM-dd".<br/>
	 */
	DateFormat DF_DATE_LEXICOGRAPHIQUE 
		= new SimpleDateFormat("yyyy-MM-dd", LOCALE_FR_FR);
	
	
	/**
	 * DF_DATETIME_LEXICOGRAPHIQUE : DateFormat :<br/>
	 * Format lexicographique des dates avec time 
	 * comme "2012-01-16_18-09-55" pour le
	 * 16 Janvier 2012 à 18 heures 9 minutes et 55 secondes.<br/>
	 * "yyyy-MM-dd_HH-mm-ss".<br/>
	 */
	DateFormat DF_DATETIME_LEXICOGRAPHIQUE 
		= new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", LOCALE_FR_FR);
	

	/**
	 * DF_DATETIME_LEXICOGRAPHIQUEMILLI : DateFormat :<br/>
	 * Format lexicographique des dates avec time 
	 * comme "2012-01-16_18-09-55-769" pour le
	 * 16 Janvier 2012 à 18 heures 9 minutes,55 secondes et 769 millisecondes.<br/>
	 * "yyyy-MM-dd_HH-mm-ss-SSS".<br/>
	 */
	DateFormat DF_DATETIME_LEXICOGRAPHIQUEMILLI 
		= new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss-SSS", LOCALE_FR_FR);


	
	/**
	 * method ecrireStringDansFileLatin9(
	 * File pFile
	 * , String pString) :<br/>
	 * SERVICE PRINCIPAL.<br/>
	 * <ul>
	 * <li>Ecrit la String pString dans le File pFile avec un encodage LATIN-9 
	 * (ISO-8859-15) 
	 * et génère un rapport d'enregistrement.<br/></li>
	 * <li>Substitue automatiquement les sauts de ligne de la plateforme
	 *  aux sauts de ligne 
	 * dans pString si nécessaire.<br/></li>
	 * <li>Utilise FileOutputStream, 
	 * new OutputStreamWriter(fileOutputStream, charset) 
	 * et BufferedWriter pour écrire.<br/></li>
	 * </ul>
	 * Ecriture dans un fichier, écriture sur disque.<br/>
	 * <br/>
	 * - passe pFile à this.fichier et 
	 * rafraîchit automatiquement this.nomFichier.<br/>
	 * - rafraîchit le rapport (en instancie un nouveau à chaque appel 
	 * de la méthode ecrireStringDansFile(File pFile)).<br/>
	 * <br/>
	 * - Passe automatiquement le Charset à CHARSET_LATIN9.<br/>
	 * - Passe automatiquement le saut de ligne à NEWLINE 
	 * (saut de ligne de la plateforme).<br/>
	 * <br/>
	 * - retourne null, LOG de niveau INFO 
	 * et rapport si pFile est null.<br/>
	 * - retourne null, LOG de niveau INFO 
	 * et rapport si pFile est inexistant.<br/>
	 * - retourne null, LOG de niveau INFO 
	 * et rapport si pFile est un répertoire.<br/>
	 * - retourne null, LOG de niveau ERROR 
	 * et rapport en cas d'Exception loggée 
	 * (FileNotFoundException, IOException).<br/>
	 * - retourne null, LOG de niveau INFO 
	 * et rapport si pString est blank.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier dans lequel on écrit.<br/>
	 * @param pString : String : String que l'on copie dans pFile.<br/>
	 * 
	 * @return : File : Le fichier dans lequel on a écrit pString.<br/>
	 */
	File ecrireStringDansFileLatin9(
			File pFile
				, String pString);
	

	
	/**
	 * method ecrireStringDansFileANSI(
	 * File pFile
	 * , String pString) :<br/>
	 * SERVICE PRINCIPAL.<br/>
	 * <ul>
	 * <li>Ecrit la String pString dans le File pFile avec un encodage ANSI 
	 * (Windows-1252 = CP1252) 
	 * et génère un rapport d'enregistrement.<br/></li>
	 * <li>Substitue automatiquement les sauts de ligne de la plateforme
	 *  aux sauts de ligne 
	 * dans pString si nécessaire.<br/></li>
	 * <li>Utilise FileOutputStream, 
	 * new OutputStreamWriter(fileOutputStream, charset) 
	 * et BufferedWriter pour écrire.<br/></li>
	 * </ul>
	 * Ecriture dans un fichier, écriture sur disque.<br/>
	 * <br/>
	 * - passe pFile à this.fichier et 
	 * rafraîchit automatiquement this.nomFichier.<br/>
	 * - rafraîchit le rapport (en instancie un nouveau à chaque appel 
	 * de la méthode ecrireStringDansFile(File pFile)).<br/>
	 * <br/>
	 * - Passe automatiquement le Charset à CHARSET_ANSI.<br/>
	 * - Passe automatiquement le saut de ligne à NEWLINE 
	 * (saut de ligne de la plateforme).<br/>
	 * <br/>
	 * - retourne null, LOG de niveau INFO 
	 * et rapport si pFile est null.<br/>
	 * - retourne null, LOG de niveau INFO 
	 * et rapport si pFile est inexistant.<br/>
	 * - retourne null, LOG de niveau INFO 
	 * et rapport si pFile est un répertoire.<br/>
	 * - retourne null, LOG de niveau ERROR 
	 * et rapport en cas d'Exception loggée 
	 * (FileNotFoundException, IOException).<br/>
	 * - retourne null, LOG de niveau INFO 
	 * et rapport si pString est blank.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier dans lequel on écrit.<br/>
	 * @param pString : String : String que l'on copie dans pFile.<br/>
	 * 
	 * @return : File : Le fichier dans lequel on a écrit pString.<br/>
	 */
	File ecrireStringDansFileANSI(
			File pFile
				, String pString);
	
	
	
	/**
	 * method ecrireStringDansFileUTF8(
	 * File pFile
	 * , String pString) :<br/>
	 * SERVICE PRINCIPAL.<br/>
	 * <ul>
	 * <li>Ecrit la String pString dans le File pFile avec un encodage UTF-8 
	 * et génère un rapport d'enregistrement.<br/></li>
	 * <li>Substitue automatiquement les sauts de ligne de la plateforme
	 *  aux sauts de ligne 
	 * dans pString si nécessaire.<br/></li>
	 * <li>Utilise FileOutputStream, 
	 * new OutputStreamWriter(fileOutputStream, charset) 
	 * et BufferedWriter pour écrire.<br/></li>
	 * </ul>
	 * Ecriture dans un fichier, écriture sur disque.<br/>
	 * <br/>
	 * - passe pFile à this.fichier et 
	 * rafraîchit automatiquement this.nomFichier.<br/>
	 * - rafraîchit le rapport (en instancie un nouveau à chaque appel 
	 * de la méthode ecrireStringDansFile(File pFile)).<br/>
	 * <br/>
	 * - Passe automatiquement le Charset à CHARSET_UTF8.<br/>
	 * - Passe automatiquement le saut de ligne à NEWLINE 
	 * (saut de ligne de la plateforme).<br/>
	 * <br/>
	 * - retourne null, LOG de niveau INFO 
	 * et rapport si pFile est null.<br/>
	 * - retourne null, LOG de niveau INFO 
	 * et rapport si pFile est inexistant.<br/>
	 * - retourne null, LOG de niveau INFO 
	 * et rapport si pFile est un répertoire.<br/>
	 * - retourne null, LOG de niveau ERROR 
	 * et rapport en cas d'Exception loggée 
	 * (FileNotFoundException, IOException).<br/>
	 * - retourne null, LOG de niveau INFO 
	 * et rapport si pString est blank.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier dans lequel on écrit.<br/>
	 * @param pString : String : String que l'on copie dans pFile.<br/>
	 * 
	 * @return : File : Le fichier dans lequel on a écrit pString.<br/>
	 */
	File ecrireStringDansFileUTF8(
			File pFile
				, String pString);
	
	
	
	/**
	 * method ecrireStringDansFile(
	 * File pFile
	 * , String pString
	 * , Charset pCharset
	 * , String pSautLigne) :<br/>
	 * SERVICE PRINCIPAL.<br/>
	 * <ul>
	 * <li>Ecrit la String pString dans le File pFile avec un encodage pCharset 
	 * et génère un rapport d'enregistrement.<br/></li>
	 * <li>Substitue automatiquement pSautLigne aux sauts de ligne 
	 * dans pString si nécessaire.<br/></li>
	 * <li>Utilise FileOutputStream, 
	 * new OutputStreamWriter(fileOutputStream, charset) 
	 * et BufferedWriter pour écrire.<br/></li>
	 * </ul>
	 * Ecriture dans un fichier, écriture sur disque.<br/>
	 * <br/>
	 * - passe pFile à this.fichier et 
	 * rafraîchit automatiquement this.nomFichier.<br/>
	 * - rafraîchit le rapport (en instancie un nouveau à chaque appel 
	 * de la méthode ecrireStringDansFile(File pFile)).<br/>
	 * <br/>
	 * - Passe automatiquement le Charset à CHARSET_UTF8 
	 * si pCharset est null.<br/>
	 * - Passe automatiquement le saut de ligne à NEWLINE 
	 * (saut de ligne de la plateforme) si pSautLigne est blank.<br/>
	 * <br/>
	 * - retourne null, LOG de niveau INFO 
	 * et rapport si pFile est null.<br/>
	 * - retourne null, LOG de niveau INFO 
	 * et rapport si pFile est inexistant.<br/>
	 * - retourne null, LOG de niveau INFO 
	 * et rapport si pFile est un répertoire.<br/>
	 * - retourne null, LOG de niveau ERROR 
	 * et rapport en cas d'Exception loggée 
	 * (FileNotFoundException, IOException).<br/>
	 * - retourne null, LOG de niveau INFO 
	 * et rapport si pString est blank.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier dans lequel on écrit.<br/>
	 * @param pString : String : String que l'on copie dans pFile.<br/>
	 * @param pCharset : Charset : Charset pour encoder le fichier.<br/>
	 * @param pSautLigne : String : Saut de ligne que l'on veut 
	 * dans pFile de sortie 
	 * (\r\n pour DOS/Windows, \r pour Mac, \n pour Unix).<br/>
	 * 
	 * @return : File : Le fichier dans lequel on a écrit pString.<br/>
	 */
	File ecrireStringDansFile(
			File pFile
				, String pString
					, Charset pCharset
						, String pSautLigne);
	
	

	/**
	 * method getDateEnregistrement() :<br/>
	 * Getter de la java.util.Date de l'enregistrement du fichier.<br/>
	 * <br/>
	 *
	 * @return dateEnregistrement : Date.<br/>
	 */
	Date getDateEnregistrement();


	
	/**
	 * method setDateEnregistrement(
	 * Date pDateEnregistrement) :<br/>
	 * Setter de la java.util.Date de l'enregistrement du fichier.<br/>
	 * <br/>
	 * - calcule automatiquement dateControleStringFormattee.<br/>
	 * <br/>
	 *
	 * @param pDateEnregistrement : Date : 
	 * valeur à passer à dateEnregistrement.<br/>
	 */
	void setDateEnregistrement(
			Date pDateEnregistrement);



	/**
	 * method getDateEnregistrementStringFormatee() :<br/>
	 * Getter de la date de l'enregistrement du fichier 
	 * formattée au format dfDatetimemilliFrancaiseLexico.<br/>
	 * Format des dates-heures françaises avec millisecondes comme
	 * '25/02/1961-12:27:07.251'.<br/>
	 * "dd/MM/yyyy-HH:mm:ss.SSS".<br/>
	 * <br/>
	 *
	 * @return dateEnregistrementStringFormatee : String.<br/>
	 */
	String getDateEnregistrementStringFormatee();



	/**
	 * method getUserName() :<br/>
	 * Getter du nom de l'utilisateur 
	 * qui a déclenché l'enregistrement du fichier.<br/>
	 * <br/>
	 *
	 * @return userName : String.<br/>
	 */
	String getUserName();



	/**
	 * method setUserName(
	 * String pUserName) :<br/>
	 * Setter du nom de l'utilisateur 
	 * qui a déclenché l'enregistrement du fichier.<br/>
	 * <br/>
	 * - remplit userName avec pUserName si pUserName != null 
     * ou 'Administrateur' sinon.<br/>
     * <br/>
	 *
	 * @param pUserName : String : valeur à passer à userName.<br/>
	 */
	void setUserName(
			String pUserName);



	/**
	 * method getObjet() :<br/>
	 * Getter de l'objet (ou motif) ayant demandé la création du fichier 
	 * comme 'contrôle de lignes vide'.<br/>
	 * <br/>
	 *
	 * @return objet : String.<br/>
	 */
	String getObjet();



	/**
	 * method setObjet(
	 * String pObjet) :<br/>
	 * Setter de l'objet (ou motif) ayant demandé la création du fichier 
	 * comme 'contrôle de lignes vide'.<br/>
	 * <br/>
	 *
	 * @param pObjet : String : valeur à passer à objet.<br/>
	 */
	void setObjet(
			String pObjet);



	/**
	 * method getFichier() :<br/>
	 * Getter du fichier enregistré.<br/>
	 * <br/>
	 *
	 * @return fichier : File.<br/>
	 */
	File getFichier();



	/**
	 * method setFichier(
	 * File pFichier) :<br/>
	 * Setter du fichier enregistré.<br/>
	 * <br/>
	 * - calcule automatiquement nomFichier.<br/>
	 * <br/>
	 *
	 * @param pFichier : File : valeur à passer à fichier.<br/>
	 */
	void setFichier(
			File pFichier);



	/**
	 * method getNomFichier() :<br/>
	 * Getter du nom du fichier objet de l'enregistrement.<br/>
	 * <br/>
	 *
	 * @return nomFichier : String.<br/>
	 */
	String getNomFichier();



} // FIN DE L'INTERFACE IEnregistreurFichiers.-------------------------------
