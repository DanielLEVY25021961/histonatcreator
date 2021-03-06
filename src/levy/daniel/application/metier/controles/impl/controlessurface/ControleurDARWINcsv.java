package levy.daniel.application.metier.controles.impl.controlessurface;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import levy.daniel.application.metier.controles.AbstractControle;
import levy.daniel.application.metier.controles.rapportscontroles.LigneRapport;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * class ControleurDARWINcsv :<br/>
 * Classe chargée de DETECTER SI UN FICHIER ENCODE EN UTF-8 
 * PEUT ETRE UN FICHIER DARWIN au format csv 
 * (donc pas un HIT ou n'importe quoi ...).<br/>
 * Contrôle dans sa méthode controler(File pFile) 
 * si le fichier pFile est de type DARWIN csv
 * et retourne true si c'est le cas.<br/>
 * Utilise pour cela le critère : 'un DARWIN csv en UTF-8 ne comporte 
 * que des lignes de 57 champs exactement'.<br/> 
 * <br/>
 * - retourne false et rapporte défavorablement 
 * si le fichier en UTF-8 comporte des lignes 
 * d'une longueur différente de 57 champs.<br/>
 * - retourne true et génère un rapport favorable si pFile 
 * ne contient que des lignes de 57 champs.<br/>
 * - Peut écrire le rapport de contrôle sous forme textuelle 
 * et csv sur disque.<br/>
 * - Génère éventuellement un rapport d'exécution de l'écriture 
 * du rapport de contrôle sur disque.<br/> 
 * - Fournit le fichier résultant du contrôle 
 * (qui peut être différent du fichier en entrée si il y a traitement) 
 * grâce à la méthode getFichierTraite().<br/>
 * <br/>
 * Attributs hérités de AbstractControle : <br/>
 * [nomClasseConcrete;ordreControle;dateControle
 * ;dateControleStringFormatee;userName;
 * fichier;nomFichier;typeControle;nomControle;nomCritere;gravite;
 * niveauAnomalie;estBloquant;aEffectuer;rapport;rapportEnregistrement;].<br/>
 * <br/>
 * Attributs de la classe :<br/>
 * néant<br/>
 * <br/>
 * <ul>
 * <li>nomClasseConcrete = "Classe ControleurDARWINcsv".</li><br/>
 * <li>ordreControle = 5.</li><br/>
 * <li>dateControle = this.date.</li><br/>
 * <li>dateControleStringFormatee = this.dateControleStringFormattee.</li><br/>
 * <li>typeControle = Contrôle de surface.</li><br/>
 * <li>nomControle = Contrôle DARWIN csv.</li><br/>
 * <li>critere = Toutes les lignes d'un DARWIN csv comportent 
 * exactement 57 champs.</li><br/>
 * <li>gravite = '3 - avertissement'.</li><br/>
 * <li>niveauAnomalie = "3".</li><br/>
 * <li>estBloquant = false.</li><br/>
 * <li>aEffectuer = true.</li><br/>
 * </ul>
 * <br/>
 * - Identifiant Enterprise Architect : CONTROLE_SURFACE_05_DARWIN.<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * Retire le caractère BOM-UTF-8 en début de ligne,<br/>
 * rapport, FileInputStream, rafraîchir le rapport, rafraichir le rapport<br/>
 * InputStreamReader, lecture ligne par ligne,<br/>
 * StringUtils.splitPreserveAllTokens(ligne, ';'),<br/> 
 * couper une ligne en préservant tous les tokens<br/>
 * splitter une chaine, <br/>
 * BufferedReader, <br/>
 * boucle while (true),<br/> 
 * <br/>
 *
 * - Dépendances :<br/>
 * .\lib\commons-lang-2.5.jar pour utiliser StringUtils 
 * de la fondation Apache.<br/>
 * .\lib_doc\commons-lang-2.5-src.zip pour la documentation de StringUtils 
 * de la fondation Apache.<br/>
 * .\lib\commons-logging-1.1.3.jar pour utiliser le logging 
 * de la fondation Apache.<br/>
 * .\lib_doc\commons-logging-1.1.1-src.zip pour la documentation du logging 
 * de la fondation Apache.<br/>
 * .\lib\javax.persistence.Transient pour l'annotation Transient 
 * provenant de hibernate-jpa-2.0-api-1.0.0.Final.jar.<br/>
 * .\lib_doc\hibernate-jpa-2.0-api-1.0.1.Final-sources.jar 
 * pour la documentation de javax.persistence.Transient pour 
 * l'annotation Transient provenant 
 * de hibernate-jpa-2.0-api-1.0.0.Final.jar.<br/>
 * levy.daniel.application.ILecteurDecodeurFile.<br/>
 * levy.daniel.application.IListeurDeCaracteresUnicode.<br/>
 * levy.daniel.application.IExportateurCsv.<br/>
 * levy.daniel.application.IExportateurJTable.<br/>
 * levy.daniel.application.IResetable.<br/>
 * levy.daniel.application.metier.controles.rapportscontroles.LigneRapport.<br/>
 * levy.daniel.application.metier.controles.IEnregistreurRapport.<br/>
 * levy.daniel.application.metier.controles.IRapporteurControle.<br/>
 * levy.daniel.application.metier.controles.IControle.<br/>
 * levy.daniel.application.metier.controles.CaractereDan.<br/>
 * levy.daniel.application.metier.services.enregistreursfichiers.IRapporteurEnregistrement.<br/>
 * levy.daniel.application.metier.services.enregistreursfichiers.IEnregistreurFichiers.<br/>
 * levy.daniel.application.metier.services.enregistreursfichiers.AbstractEnregistreurFichiers.<br/>
 * levy.daniel.application.metier.services.enregistreursfichiers.impl.EnregistreurFichiers.<br/>
 * levy.daniel.application.metier.services.enregistreursfichiers.rapportsenregistrements.LigneRapportEnregistrement.<br/>
 * levy.daniel.application.metier.controles.AbstractControle.<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 24 mars 2016
 *
 */
public class ControleurDARWINcsv extends AbstractControle {

	// ************************ATTRIBUTS************************************/

	/**
	 * CLASSE_CONTROLEURDARWIN : String :<br/>
	 * "Classe ControleurDARWINcsv".<br/>
	 */
	public static final String CLASSE_CONTROLEURDARWIN 
	= "Classe ControleurDARWINcsv";
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(ControleurDARWINcsv.class);
	

	// *************************METHODES************************************/

	
	 /**
	 * method CONSTRUCTEUR ControleurDARWINcsv() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 * - Met automatiquement 5 dans this.ordreControle.<br/>
	 * - Met automatiquement dateControle à date système.<br/>
	 * - Met automatiquement userName à "Administrateur".<br/>
	 * - Met automatiquement fichier à null.<br/>
	 * <br/>
	 * - Remplit le nom de la classe concrète this.nomClasseConcrete fourni 
	 * par this.fournirNomClasseConcrete() dans la classe concrète.<br/>
	 * - calcule automatiquement dateControleStringFormattee.<br/>
	 * - calcule automatiquement nomFichier.<br/>
	 * - Remplit le type du contrôle typeControle fourni par 
	 * this.fournirTypeControle() dans la classe concrète.<br/>
	 * - Remplit le nom du contrôle nomControle fourni par 
	 * this.fournirNomControle() dans la classe concrète.<br/>
	 * - Remplit le nom du critère nomCritere fourni par 
	 * this.fournirNomCritere() dans la classe concrète.<br/>
	 * - Remplit gravite (ce qui remplit également niveauAnomalie).<br/>
	 * - Va chercher dans messagescontroles_fr_FR.properties 
	 * si le contrôle doit être effectué et remplit this.aEffectuer.<br/>
	 * <br/>
	 */
	public ControleurDARWINcsv() {
		
		this(5, null, null, null);
		
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * method CONSTRUCTEUR ControleurDARWINcsv(
	 * File pFichier) :<br/>
	 * Constructeur avec fichier.<br/>
	 * <br/>
	 * - Met automatiquement 5 dans this.ordreControle.<br/>
	 * - Met automatiquement dateControle à date système.<br/>
	 * - Met automatiquement userName à "Administrateur".<br/>
	 * <br/>
	 * <ul>
	 * <li>Remplit le nom de la classe concrète this.nomClasseConcrete 
	 * fourni par this.fournirNomClasseConcrete() dans la classe concrète.</li><br/>
	 * <li>calcule automatiquement dateControleStringFormattee.</li><br/>
	 * <li>passe pFichier à this.fichier.</li><br/>
	 * <li>calcule automatiquement nomFichier.</li><br/>
	 * <li>Remplit le type du contrôle typeControle fourni par 
	 * this.fournirTypeControle() dans la classe concrète.</li><br/>
	 * <li>Remplit le nom du contrôle nomControle fourni par 
	 * this.fournirNomControle() dans la classe concrète.</li><br/>
	 * <li>Remplit le nom du critère nomCritere fourni par 
	 * this.fournirNomCritere() dans la classe concrète.</li><br/>
	 * <li>Remplit gravite (ce qui remplit également niveauAnomalie).</li><br/>
	 * <li>Va chercher dans messagescontroles_fr_FR.properties 
	 * si le contrôle doit être effectué et remplit this.aEffectuer.</li><br/>
	 * </ul>
	 * <br/>
	 *
	 * @param pFichier : File : fichier sur lequel s'applique le contrôle.<br/>
	 */
	public ControleurDARWINcsv(
			final File pFichier) {
		
		this(5, null, null, pFichier);
		
	} // Fin de ControleurDARWINcsv(
	 // File pFichier).____________________________________________________

	
	
	 /**
	 * method CONSTRUCTEUR ControleurDARWINcsv(
	 * String pUserName
	 * , File pFichier) :<br/>
	 * Constructeur avec user et fichier.<br/>
	 * <br/>
	 * - Met automatiquement 5 dans this.ordreControle.<br/>
	 * - Met automatiquement dateControle à date système.<br/>
	 * <br/>
	 * <ul>
	 * <li>initialise éventuellement le bundleControles qui encapsule 
	 * messagescontroles_fr_FR.properties.</li><br/>
	 * <li>Remplit le nom de la classe concrète this.nomClasseConcrete 
	 * fourni par this.fournirNomClasseConcrete() dans la classe concrète.</li><br/>
	 * <li>calcule automatiquement dateControleStringFormattee.</li><br/>
	 * <li>remplit userName avec pUserName si pUserName != null 
	 * ou 'Administrateur' sinon.</li><br/>
	 * <li>passe pFichier à this.fichier.</li><br/>
	 * <li>calcule automatiquement nomFichier.</li><br/>
	 * <li>Remplit le type du contrôle typeControle fourni par 
	 * this.fournirTypeControle() dans la classe concrète.</li><br/>
	 * <li>Remplit le nom du contrôle nomControle fourni par 
	 * this.fournirNomControle() dans la classe concrète.</li><br/>
	 * <li>Remplit le nom du critère nomCritere fourni par 
	 * this.fournirNomCritere() dans la classe concrète.</li><br/>
	 * <li>Remplit gravite (ce qui remplit également niveauAnomalie).</li><br/>
	 * <li>Va chercher dans messagescontroles_fr_FR.properties 
	 * si le contrôle doit être effectué et remplit this.aEffectuer.</li><br/>
	 * </ul>
	 * <br/>
	 *
	 * @param pUserName : String : nom de l'utilisateur 
	 * qui a déclenché le contrôle.<br/> 
	 * @param pFichier : File : fichier sur lequel s'applique le contrôle.<br/>
	 */
	public ControleurDARWINcsv(
			final String pUserName
					, final File pFichier) {
		
		this(5, null, pUserName, pFichier);
		
	} // Fin de ControleurDARWINcsv(
	 // String pUserName
	 // , File pFichier).__________________________________________________
	
	

	/**
	 * method CONSTRUCTEUR ControleurDARWINcsv(COMPLET) :<br/>
	 * CONSTRUCTEUR COMPLET.<br/>
	 * <br/>
	 * <ul>
	 * <li>initialise éventuellement le bundleControles qui encapsule 
	 * messagescontroles_fr_FR.properties.</li><br/>
	 * <li>Remplit le nom de la classe concrète this.nomClasseConcrete fourni 
	 * par this.fournirNomClasseConcrete() dans la classe concrète.</li><br/>
	 * <li>passe pOrdreControle à this.ordreControle.</li><br/>
	 * <li>Remplit dateControle avec pDateControle si pDateControle != null 
	 * ou la date système sinon.</li><br/>
	 * <li>calcule automatiquement dateControleStringFormattee.</li><br/>
	 * <li>remplit userName avec pUserName si pUserName != null 
	 * ou 'Administrateur' sinon.</li><br/>
	 * <li>passe pFichier à this.fichier.</li><br/>
	 * <li>calcule automatiquement nomFichier.</li><br/>
	 * <li>Remplit le type du contrôle typeControle fourni par 
	 * this.fournirTypeControle() dans la classe concrète.</li><br/>
	 * <li>Remplit le nom du contrôle nomControle fourni par 
	 * this.fournirNomControle() dans la classe concrète.</li><br/>
	 * <li>Remplit le nom du critère nomCritere fourni par 
	 * this.fournirNomCritere() dans la classe concrète.</li><br/>
	 * <li>Remplit gravite (ce qui remplit également niveauAnomalie 
	 * et estBloquant).</li><br/>
	 * <li>Va chercher dans messagescontroles_fr_FR.properties 
	 * si le contrôle doit être effectué et remplit this.aEffectuer.</li><br/>
	 * </ul>
	 * <br/>
	 *
	 * @param pOrdreControle : Integer : ordre d'exécution du contrôle
	 * @param pDateControle : Date : java.util.Date du contrôle.<br/>
	 * @param pUserName : String : nom de l'utilisateur 
	 * qui a déclenché le contrôle.<br/> 
	 * @param pFichier : File : fichier sur lequel s'applique le contrôle.<br/>
	 */
	public ControleurDARWINcsv(
			final Integer pOrdreControle
			, final Date pDateControle
				, final String pUserName
					, final File pFichier) {
		
		super(pOrdreControle, pDateControle, pUserName, pFichier);
		
	} // Fin de CONSTRUCTEUR COMPLET.______________________________________
	

	
	/**
	 * method controlerHook(
	 * File pFile
	 * , boolean pEnregistrerRapport) :<br/>
	 * Contrôle si le fichier pFile est susceptible 
	 * d'être un DARWIN au format csv
	 * et retourne true si c'est le cas.<br/>
	 * ATTENTION : Le fichier soumis est supposé être encodé en UTF-8.<br/>
	 * <ul>
	 * <li>Utilise pour celà le critère 
	 * 'Toutes les lignes d'un DARWIN au format csv comportent
	 * 57 champs'.</li><br/>
	 * <li>Lit le fichier ligne par ligne en UTF-8 en utilisant 
	 * un BufferedReader(InputStreamReader(fileInputStream, CHARSET_UTF8)) 
	 * et détecte les nombres de champs en splittant la chaîne 
	 * avec le méthode StringUtils.splitPreserveAllTokens(ligne, ';').</li><br/>
	 * <li>Enregistre le rapport de contrôle sur disque 
	 * si pEnregistrerRapport == true.</li><br/>
	 * </ul> 
	 * <br/>
	 * <ul>
	 * <li>retourne false et rapporte défavorablement si une ligne 
	 * ne comporte pas 57 champs.</li><br/>
	 * <li>retourne true et rapporte favorablement 
	 * si toutes les lignes comportent 57 champs.</li><br/>
	 * <br/>
	 * <li>passe pFile à this.fichier et 
	 * rafraîchit automatiquement this.nomFichier.</li><br/>
	 * <li>rafraîchit le rapport (en instancie un nouveau 
	 * à chaque appel de la méthode controler(File pFile)).</li><br/>
	 * </ul>
	 * <br/>
	 * - retourne false, LOG de niveau INFO et rapport si pFile == null.<br/>
	 * - retourne false, LOG de niveau INFO et rapport si pFile 
	 * est inexistant.<br/>
	 * - retourne false, LOG de niveau INFO et rapport si pFile 
	 * est un répertoire.<br/>
	 * - retourne false, LOG de niveau INFO et rapport si pFile 
	 * est vide.<br/>
	 * <br/>
	 * RG-01-05 : Ligne DARWIN csv à 57 champs.<br/>
	 * <br/>
	 * 
	 * 
	 * @param pFile : File : fichier dont on veut savoir 
	 * si il est susceptible d'être un Darwin au format csv.<br/>
	 * @param pEnregistrerRapport : boolean : 
	 * true si on veut enregistrer le rapport dans un fichier sur disque.<br/>
	 * 
	 * @return : boolean : true si pFile est susceptible 
	 * d'être un DARWIN au format csv.<br/>
	 */
	@Override
	protected final boolean controlerHook(
			final File pFile
				, final boolean pEnregistrerRapport) {

		// LECTURE ***************
		FileInputStream fileInputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;

		int numeroLigne = 0;
		boolean resultat = true;
		boolean resultatFichier = true;

		try {

			// LECTURE DU FICHIER LIGNE PAR LIGNE. *********************
			/*
			 * Instancie un flux en lecture fileInputStream 
			 * en lui passant pFile.
			 */
			fileInputStream 
				= new FileInputStream(pFile);

			/*
			 * Instancie un InputStreamReader 
			 * en lui passant le FileReader et le
			 * Charset UTF-8.
			 */
			inputStreamReader 
				= new InputStreamReader(fileInputStream
						, CHARSET_UTF8);

			/*
			 * Instancie un tampon de flux de caractères en lecture en lui
			 * passant le flux inputStreamReader.
			 */
			bufferedReader 
				= new BufferedReader(inputStreamReader);

			// LECTURE LIGNE PAR LIGNE.**********************************
			/* Parcours du bufferedReader. */
			while (true) {

				/* incrémente le numéro de la ligne lue. */
				numeroLigne++;

				/* Lecture ligne par ligne. */
				final String ligneLue = bufferedReader.readLine();

				/* Arrêt de la lecture si fin de fichier. */
				if (ligneLue == null) {
					break;
				}
				
				// VERIFICATION DU CRITERE NOMBRE DE CHAMPS.---
				resultat = this.determinerBonneLongueur(ligneLue);
				
				/* RAPPORT DE LIGNE 
				 * si la ligne n'a pas une bonne longueur. */
				if (!resultat) {
					
					resultatFichier = false;
					
					/* Constitution du message 
					 * pour le rapport de ligne défavorable. */
					final StringBuilder stb = new StringBuilder();
					
					stb.append("La ligne numéro ");
					stb.append(numeroLigne);
					stb.append(" ne comporte pas 57 champs");
					stb.append(" comme exigé dans un DARWIN csv.");
					stb.append(" Elle comporte ");
					stb.append(StringUtils.splitPreserveAllTokens(ligneLue, ';').length);
					stb.append(" champs");
					
					final String message = stb.toString();
					
					/* rapport. */
					final LigneRapport ligneRapport 
						= creerLigneRapport(
								numeroLigne
								, message
								, null
								, SANS_OBJET
								, SANS_OBJET
								, false
								, ACTION_LIGNE_NON_DARWIN);
					
					/* Ajoute une ligne au rapport 
					 * pour le caractère déficient. */
					this.ajouterLigneRapport(ligneRapport);
					
				} // Fin de if (!resultat)._____________________

			} // Fin du parcours du bufferedReader.______________********

		} catch (FileNotFoundException fnfe) {

			/* LOG de niveau ERROR. */
			loggerError(this.fournirNomClasseConcrete()
					, METHODE_CONTROLER
						, fnfe);

			/* retourne false si exception. */
			return false;

		} catch (IOException ioe) {

			/* LOG de niveau ERROR. */
			loggerError(this.fournirNomClasseConcrete()
					, METHODE_CONTROLER
						, ioe);

			/* retourne false si exception. */
			return false;
		}

		finally {

			/* fermeture du flux BufferedReader. */
			if (bufferedReader != null) {

				try {

					bufferedReader.close();

				} catch (IOException ioe2) {

					/* LOG de niveau ERROR. */
					loggerError(this.fournirNomClasseConcrete()
							, METHODE_CONTROLER
								, ioe2);
				}

			} // Fin de if (bufferedReader != null).____

			/* fermeture du flux inputStreamReader. */
			if (inputStreamReader != null) {

				try {

					inputStreamReader.close();

				} catch (IOException ioe4) {

					/* LOG de niveau ERROR. */
					loggerError(this.fournirNomClasseConcrete(),
							METHODE_CONTROLER, ioe4);
				}

			} // Fin de if (inputStreamReader != null).______

			/* fermeture du flux fileInputStream. */
			if (fileInputStream != null) {

				try {

					fileInputStream.close();

				} catch (IOException ioe3) {

					/* LOG de niveau ERROR. */
					loggerError(this.fournirNomClasseConcrete(),
							METHODE_CONTROLER, ioe3);

				}

			} // Fin de if (fileInputStream != null).________

		} // Fin du finally._____________________________

		
		// AJOUT DES RAPPORTS DE NIVEAU FICHIER 
		// et ENREGISTREMENT éventuel SUR DISQUE.************************
		/* rapport de niveau fichier si le contrôle est favorable. */	
		if (resultatFichier) {	
			
			/* Constitution du message favorable. */
			final StringBuilder stb = new StringBuilder();
			
			stb.append("Le fichier '");
			stb.append(pFile.getName());
			stb.append("' semble bien être un fichier DARWIN csv");
			stb.append(" (toutes les lignes lues en UTF-8");
			stb.append(" comportent 57 champs)");
						
			final String message = stb.toString();
			
			/* Ajout d'une ligne de rapport favorable
			 * au rapport de contrôle et enrgistrement 
			 * sur disque si pEnregistrerRapport. */
			this.ajouterLigneRapportFavorableNiveauFichier(
					message, pEnregistrerRapport);
			
		} // Fin de if (resultat)._____________
		
		/* rapport de niveau fichier si le contrôle est défavorable. */
		else {
			
			/* Constitution du message défavorable. */
			final StringBuilder stb = new StringBuilder();
			
			stb.append("Le fichier '");
			stb.append(pFile.getName());
			stb.append("' ne peut pas être un fichier DARWIN csv");
			stb.append(" (toutes les lignes lues en UTF-8");
			stb.append(" devraient comporter 57 champs)");
						
			final String message = stb.toString();
			
			/* Création d'une ligne rapport 
			 * pour le rapport de contrôle défavorable. */
			/* Spécifique, car le fait que le fichier 
			 * ne soit pas un DARWIN n'est pas bloquant. */
			final LigneRapport ligneRapport 
			= this.creerLigneRapport(
					null
					, message
					, null
					, SANS_OBJET
					, SANS_OBJET
					, true
					, ACTION_FICHIER_ACCEPTE);
			
			/* Ajout d'une ligne de rapport défavorable
			 * au rapport de contrôle et enregistrement sur disque 
			 * si pEnregistrerRapport. */
			this.ajouterLigneRapport(ligneRapport);
			
			/* Enregistrement du rapport sur disque. */
			if (pEnregistrerRapport) {
								
				this.enregistrerRapportTextuelUTF8(
						this.fournirFileTxtUTF8());
				this.enregistrerRapportCsvUTF8(
						this.fournirFileCsvUTF8());
				
			} // Fin de if (pEnregistrerRapport).________________
			
		} // Fin de (!if (resultat))._____________
		
		
		// RETOUR DU RESULTAT.*****************************************
		/* retourne false et rapporte si 
		 * une ligne ne comporte pas 57 champs. */
		/* retourne true et rapporte si 
		 * le fichier a bien toutes ses lignes à 57 champs. */
		return resultatFichier;

	} // Fin de controlerHook(
	// File pFile
	// , boolean pEnregistrerRapport)._____________________________________


	
	/**
	 * method determinerBonneLongueur(
	 * String pLigne) :<br/>
	 * <ul>
	 * <li>Retourne true si pLigne comporte 57 champs.</li><br/>
	 * <li>Retourne false sinon.</li><br/>
	 * <li>Retire éventuellement le caractère BOM-UTF-8 
	 * en début de ligne.</li><br/>
	 * </ul>
	 * <br/>
	 * - retourne false si pLigne est blank.<br/>
	 * <br/>
	 *
	 * @param pLigne : String.<br/>
	 * 
	 * @return : boolean : true si pLigne comporte 57 champs.<br/>
	 */
	private boolean determinerBonneLongueur(
			final String pLigne) {
		
		/* retourne false si pLigne est blank. */
		if (StringUtils.isBlank(pLigne)) {
			return false;
		}
		
		boolean resultat = true;
		String ligne = null;
		
		/* Retire éventuellement le caractère BOM-UTF-8 
		 * en début de ligne. */
		if (pLigne.charAt(0) == BOM_UTF_8) {
			ligne = pLigne.substring(1);
		}
		else {
			ligne = pLigne;
		}
		
		
		/* Récupération du nombre de champs. */
		final int longueur 
			= StringUtils.splitPreserveAllTokens(ligne, ';').length;
		
		/* retourne false si la longueur ne vaut pas 57 champs. */
		if (longueur != 57) {
			resultat = false;
		}
		
		return resultat;
		
	} // Fin de determinerBonneLongueur(
	 // String pLigne).____________________________________________________
	
	
	
	/**
	 * "Classe ControleurDARWINcsv".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	protected final String fournirNomClasseConcrete() {
		return CLASSE_CONTROLEURDARWIN;
	} // Fin de fournirNomClasseConcrete().________________________________

	
	
	/**
	 * "Contrôle de surface".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	protected final String fournirTypeControle() {
		return "Contrôle de surface";
	} // Fin de fournirTypeControle()._____________________________________
	
	

	/**
	 * "Contrôle DARWIN csv".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	protected final String fournirNomControle() {
		return "Contrôle DARWIN csv";
	} // Fin de fournirNomControle().______________________________________

	
	
	/**
	 * "Toutes les lignes d'un DARWIN csv comportent exactement 57 champs".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	protected final String fournirNomCritere() {
		return "Toutes les lignes d'un DARWIN csv comportent exactement 57 champs";
	} // Fin de fournirNomCritere()._______________________________________
	
	

	/**
	 * "ControleurDARWINcsv.niveau.anomalie".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	protected final String fournirCleNiveauAnomalie() {
		return "ControleurDARWINcsv.niveau.anomalie";
	} // Fin de fournirCleNiveauAnomalie().________________________________
	
	

	/**
	 * "3".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	protected final String fournirNiveauAnomalieEnDur() {
		return "3";
	} // Fin de fournirNiveauAnomalieEnDur().______________________________

	

	/**
	 * "RAPPORT-CONTROLE-DARWIN".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	protected final String fournirBaseNomRapport() {
		return "RAPPORT-CONTROLE-DARWIN";
	} // Fin de fournirBaseNomRapport().___________________________________

	
	
	/**
	 * "ControleurDARWINcsv.aEffectuer".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	protected final String fournirCleAEffectuer() {
		return "ControleurDARWINcsv.aEffectuer";
	} // Fin de fournirCleAEffectuer().____________________________________
	
	

	/**
	 * true.<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	protected final boolean fournirAEffectuerEnDur() {
		return true;
	} // Fin de fournirAEffectuerEnDur().__________________________________
	

	
	/**
	 * this.fichier.<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final File getFichierTraite() {
		return this.fichier;
	} // Fin de getFichierTraite().________________________________________



} // FIN DE LA CLASSE ControleurDARWINcsv.-----------------------------------
