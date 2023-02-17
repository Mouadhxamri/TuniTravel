<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220415000346 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE user CHANGE nom_client nom_client VARCHAR(15) NOT NULL, CHANGE prenom_client prenom_client VARCHAR(15) NOT NULL, CHANGE CIN CIN VARCHAR(8) NOT NULL, CHANGE numtel_client numtel_client VARCHAR(8) NOT NULL, CHANGE num_passport num_passport VARCHAR(10) NOT NULL, CHANGE datteN_client datteN_client DATE NOT NULL, CHANGE role role VARCHAR(10) NOT NULL');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE user CHANGE nom_client nom_client VARCHAR(15) DEFAULT NULL, CHANGE prenom_client prenom_client VARCHAR(15) DEFAULT NULL, CHANGE CIN CIN VARCHAR(8) DEFAULT NULL, CHANGE numtel_client numtel_client VARCHAR(8) DEFAULT NULL, CHANGE num_passport num_passport VARCHAR(10) DEFAULT NULL, CHANGE datteN_client datteN_client DATE DEFAULT NULL, CHANGE role role VARCHAR(10) DEFAULT NULL');
    }
}
