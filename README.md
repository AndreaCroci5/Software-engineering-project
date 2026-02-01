# IS24-AM40


## Codex Naturalis



### About

Codex Naturalis is a board game made by the italian company Cranio Creation whose collaboration with Politecnico di Milano gives to students enrolled in **"Computer Science Engineering"** an opportunity to make a Java implementation of the yearly game released by the company as a part of the final project of **"Software Engineering"** course.


### Implemented Functionalities

| Functionality              |            Status             |
|:---------------------------|:-----------------------------:|
| Basic rules                |             [ Yes ]               |
| Complete rules             |             [ Yes ]               |
| Socket                     |             [ Yes ]               |
| RMI                        |             [ No ]               |
| CLI                        |             [ Yes ]               |
| GUI                        |             [ Yes ]               |
| AF1 (Multiple Games)       |             [ Yes ]           |
| AF2 (Chat)                 |             [ No ]            |
| AF3opt1 (Persistence)      |             [ No ]            |
| AF3opt2 (Persistence)      |             [ No ]            |


### The Team
* [Simone Boscain](https://github.com/Simobosca02)
* [Andrea Croci](https://github.com/AndreaCroci5)
* [Giovanni De Marco](https://github.com/DeMarcoGiovanni)
* [Nicolò Di Carlo](https://github.com/NicoloDiCarlo)


### Software Used

 **Astah UML** - UML diagrams
 
 **Intellij IDEA Ultimate** - main IDE

### Disclaimer
Codex Naturalis è un gioco da tavolo sviluppato ed edito da Cranio Creations Srl. I contenuti grafici di questo progetto riconducibili al prodotto editoriale da tavolo sono utilizzati previa approvazione di Cranio Creations Srl a solo scopo didattico. È vietata la distribuzione, la copia o la riproduzione dei contenuti e immagini in qualsiasi forma al di fuori del progetto, così come la redistribuzione e la pubblicazione dei contenuti e immagini a fini diversi da quello sopracitato. È inoltre vietato l'utilizzo commerciale di suddetti contenuti.

### My part
- Design and implementation of the PrivateBoard with card positioning logic, algorithms and everything related to game cards.
- Design and implementation of the Actions, core of the Listener and Command patterns responsible of the Server behaviour.
- Design and implementation of Data and the network data serialization protocol, Data is the network counterpart of the Actions, just like the Messages are for the client.
- Design and implementation of the GUI.

### To fix
- Aim Cards postion check is wrong since it uses a wrong coordinate system.
- RMI.
- Check network concurrency and logging..