# Company Obligation Tracker (COT)

Gestionnaire de suivi des obligations d'entreprise avec une architecture robuste, sécurisée et évolutive. Ce projet est conçu pour automatiser le monitoring des engagements contractuels et financiers, tout en intégrant une gestion complète du capital humain.

---

## Vue d'Ensemble

Le système **Company Obligation Tracker** est une solution "Backend-First" développée avec **Spring Boot 3**. Il permet à une organisation de centraliser, suivre et auditer l'ensemble de ses obligations envers des tiers, ses employés ou des organismes tiers. 

L'approche modulaire et l'usage de bibliothèques modernes garantissent une maintenance simplifiée et une sécurité de niveau entreprise.

---

## Caractéristiques Principales

### 1. Suivi des Obligations
- Cycle de vie complet des obligations (création, suivi, paiement, clôture).
- Catégorisation intelligente et priorisation des échéances.
- Statuts de paiement en temps réel.

### 2. Gestion RH et Absences
- Administration des employés et de leurs profils professionnels.
- Suivi rigoureux des absences et impacts sur les obligations salariales.
- Classification par catégories de métier.

### 3. Monitoring et Alertes
- Système d'alertes automatisé pour prévenir les retards.
- Tableau de bord synthétique avec indicateurs de performance (KPIs).
- Notifications basées sur la criticité des échéances.

### 4. Sécurité et Audit
- Authentification et Autorisation via **Spring Security**.
- Échange de données sécurisé par **JSON Web Token (JWT)**.
- Module **Historique** dédié à la traçabilité complète de chaque action utilisateur.

---

## Stack Technique

| Composant | Technologie |
| :--- | :--- |
| **Langage** | Java 17 |
| **Framework Principal** | Spring Boot 3.2.3 |
| **Sécurité** | Spring Security & JJWT (0.12.5) |
| **Persistance des données** | Spring Data JPA / Hibernate |
| **Base de Données** | PostgreSQL |
| **Mapping d'Objets** | MapStruct 1.5.5 |
| **Productivité** | Lombok |
| **Validation** | Bean Validation (Hibernate Validator) |
| **Tests Unitaires / Mock** | JUnit 5 & Mockito |

---

## Conception et Architecture

Le projet repose sur une modélisation UML rigoureuse pour assurer la cohérence du domaine métier.

### Diagramme des Cas d'Utilisation
Ce diagramme illustre les interactions possibles entre les acteurs du système et les différentes fonctionnalités.

![Diagramme de Cas d'Utilisation](UML/COT-usecases-diagramme.jpg)

### Diagramme de Classes
Ce diagramme détaille la structure des données et les relations entre les entités (Obligation, Employé, Alerte, Historique).

![Diagramme de Classes](UML/COT-classes-diagramme.jpg)

---

## Installation et Configuration

### Prérequis
- Java JDK 17 ou supérieur.
- Maven 3.8+.
- PostgreSQL 14+.

### Procédure de Lancement
1. **Cloner le dépôt** :
   ```bash
   git clone [url-du-repo]
   cd company-obligation-tracker/demo
   ```

2. **Configurer la base de données** :
   Modifier le fichier `src/main/resources/application.properties` avec vos identifiants PostgreSQL :
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/nom_bdd
   spring.datasource.username=votre_user
   spring.datasource.password=votre_password
   ```

3. **Compilation et Installation** :
   ```bash
   mvn clean install
   ```

4. **Exécution** :
   ```bash
   mvn spring-boot:run
   ```

---

## Qualité et Tests

L'accent a été mis sur la fiabilité du code via une suite de tests unitaires et d'intégration utilisant Mockito pour le bouchonnage des services et JUnit 5 pour les assertions.

```bash
# Pour exécuter les tests
mvn test
```

---

## Auteurs et Contributions

Ce projet a été réalisé avec une attention particulière portée à la propreté du code (Clean Code) et au respect des principes SOLID. 

> [!NOTE]  
> Ce système de gestion des obligations est une solution extensible permettant d'ajouter de nouveaux types d'engagements ou de nouvelles intégrations tierces sans altérer le cœur de l'application.
