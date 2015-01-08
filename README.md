Elaborato ingegneria del software - Parte 2 

La documentazione su cui lavorare è quella condivisa su dropbox (Documentazione - Parte2). Ricordarsi di caricare le modifiche fatte e di non modificare contemporaneamente. 
Per scrivere la documentazione ho usato Word 2010, spero che non dia problemi.

Procedimento :

1) Fare il refactoring delle proprie classi o package (la classe diagnosi si trova in gestioneTS, le probabilità e le distanze si trovano in statisticheGuasti.
Per ogni tecnica di refactoring (extract method, renaming, move method, replace temporary) elencare le modifiche effettuate in una tabella simile a quelle già messe.

2) Usare il design pattern state per i metodi di probabilità. Cercare su internet o sulle slides della zanella per sapere come si usa. Spiegare le classi usate e le loro relazioni e mettere il diagramma delle classi, solo per le classi coinvolte nel pattern.

3) Fare il testing. Completare i casi mancanti nel testing del menù principale (sono quelli in rosso che non ho completato perchè mancano le probabilità). Se il caso di test da esito negativo, scrivere in rosso e spiegare in una nota quale è il problema. Le modifiche fatte per sistemare i problemi evidenziati dal testing sono interventi di manutenzione correttiva, quindi ricordarsi di metterli nel successivo capitolo.
Fare il testing delle diagnosi, delle probabilità (sia metodo 1 che metodo 2) e delle distanze.

Consigli testing diagnosi : provare diverse combinazioni di possibili dati in ingresso considerando i boundary values che potrebbero dare risultati strani. Considerare ad esempio una sola coppia con un solo cammino e rilevazione OK (e poi KO). Considerare poi test suite normali e verificare i risultati. Cercare combinazioni particolari che possono dare insiemi delle diagnosi vuote. 

Consigli testing probabilità : provare con un'unica azione e valore della rilevazione prima OK e poi KO. Verificare che si ottengano i risultati giusti. Cercare combinazioni particolari di dati in ingresso che possano dare per alcune azioni probabilità di guasto ignota (per il metodo 1). Provare con test suite normali, variando le cardinalità delle classi di equivalenza. 
Eseguire ciascun caso di test su entrambi i metodi e verificare che si ottenga sempre un numero compreso tra 0 e 1. 
Per verificare che le probabilità siano corrette si può fare il calcolo a mano.

Consigli testing distanze : Provare con elenchi a distanza zero, azioni con la  stessa probabilità e altre combinazioni particolari che possono dare risultati inaspettati. Verificare sempre la correttezza dei risultati ottenuti in base alle specifiche.

4) Manutenzione. 
La manutenzione correttiva comprende tutte le modifiche fatte per sistemare i problemi rilevati dal testing. La manutenzione perfettiva invece si fa solo se ci si accorge che qualcosa non viene fatto secondo le specifiche o se ci si è dimenticati di soddisfare qualche requisito. 


