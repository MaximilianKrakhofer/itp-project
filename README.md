
Projekt: ITP-Lernplattform
Projektbeschreibung 
Das Projekt ist eine Lernplattform zur spielerischen Unterstützung von IT-Projekten (ITP). Es ermöglicht Benutzern, über ein grafisches Benutzerinterface (GUI) Fragen zu verwalten, Quizze zu spielen und ein Hangman-Spiel zu nutzen. Die Hauptzielgruppe sind Schüler und Lehrer, insbesondere an der HTL und TGM, die den Lernfortschritt durch interaktive Lernmethoden fördern wollen.

Zusätzlich können Lehrpersonen Karteikarten exportieren und mit Schülern teilen, was das gemeinsame Lernen erleichtert. Weitere Spielmodule lassen sich durch den Hersteller modular hinzufügen, um die Funktionalität der Anwendung zu erweitern.

Ziel des Projekts
Das Ziel der Plattform ist es, das Lernen von ITP-Themen durch spielerische Ansätze zu unterstützen und zu personalisieren. Der Lernfortschritt wird in Form von Statistiken festgehalten, die nach jedem Quiz oder Hangman-Spiel angezeigt werden. Die Anwendung richtet sich insbesondere an Lehrer, die ihren Schülern eine spielerische Lernumgebung bieten möchten.

Funktionen
Hauptmenü
Das Hauptmenü ist der zentrale Ausgangspunkt der Anwendung und bietet schnellen Zugang zu allen Hauptfunktionen:

Fragenverwaltung: Zum Laden, Bearbeiten und Speichern von Fragen.
Quiz: Testen des Wissens in einem Quizformat.
Hangman-Spiel: Ein klassisches Hangman-Spiel mit Fragen aus dem Fragenpool.
Fragenverwaltung
In der Fragenverwaltung können Benutzer Fragen verwalten:

Fragen laden: Ein bestehender Fragenpool kann aus einer Textdatei importiert werden.
Fragen hinzufügen: Neue Fragen können als Text oder URL (für Bilder) hinzugefügt werden. Die Antworten müssen als Text eingetragen werden.
Fragen bearbeiten: Bereits gespeicherte Fragen und Antworten können bearbeitet werden.
Fragen löschen: Einzelne Fragen können aus dem Pool entfernt werden.
Fragen speichern: Der gesamte Fragenpool kann als Textdatei gespeichert werden.
Quiz
Das Quiz stellt dem Benutzer Fragen aus dem geladenen Fragenpool in zufälliger Reihenfolge. Nach jeder Frage wird die Antwort des Benutzers mit der korrekten Lösung verglichen und Punkte werden für die Statistik gesammelt.

Hangman-Spiel
Das Hangman-Spiel verwendet ebenfalls den Fragenpool, um den Benutzer Fragen zu stellen. Bei jeder falschen Antwort wird ein Teil des Hangman-Männchens gezeichnet. Nach 10 Fehlern ist das Spiel verloren. Am Ende des Spiels wird eine Statistik angezeigt, die den Lernfortschritt visualisiert.

Produktdaten
Es werden folgende Daten persistent gespeichert:

Fragenmanagementdaten: Enthält Informationen zu allen Karteikarten (Fragen und Antworten).
Quizstatistik: Daten zu korrekt und inkorrekt beantworteten Fragen.
Hangmanstatistik: Daten zu korrekt und inkorrekt beantworteten Fragen im Hangman-Spiel.
Produktleistung
Karten-Import/Export: Der Import und Export von Karten darf maximal 4 Sekunden dauern.
Ladezeiten: Die Benutzeroberfläche sollte innerhalb von 1 Sekunde laden. Fragen und Statistiken sollen in weniger als 0,5 Sekunden geladen werden.
Interaktionsfeedback: Buttons wie "Löschen" oder "Exportieren" sind nur klickbar, wenn eine Karte ausgewählt ist. Fehlerhafte Dateitypen beim Importieren erzeugen eine Fehlermeldung.
Qualitätsanforderungen
Die Plattform legt großen Wert auf:

Funktionalität: Einfache Erweiterbarkeit durch modular hinzufügbare Spielmodi.
Zuverlässigkeit: Schnell ladende Fragen und Statistiken.
Benutzbarkeit: Eine benutzerfreundliche und anpassbare Oberfläche.
Effizienz: Schnelle Interaktionen und Reaktionszeiten.

Dieses Projekt zielt darauf ab, das Lernen von IT-Projekt-Fragen in einem spielerischen und interaktiven Kontext zu unterstützen. Durch die modulare Erweiterbarkeit bleibt die Anwendung flexibel und zukunftssicher.






