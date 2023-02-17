<?php

namespace App\Entity;

use App\Repository\ChambreRepository;
use Doctrine\ORM\Mapping as ORM;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;

use Symfony\Component\Validator\Constraints\GreaterThan;
use Symfony\Component\Validator\Constraints\NotBlank;
use Symfony\Component\Validator\Constraints\Date;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * @ORM\Entity(repositoryClass=ChambreRepository::class)
 */
class Chambre
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @Assert\NotBlank(message="Ref doit etre non vide")
     * @Assert\Length(
     * max = 100, 
     * maxMessage="Longueur maximale est 100"
     * )
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $type;

    /**
     * @Assert\NotBlank(message="Ref doit etre non vide")
     * @Assert\Length(
     * max = 100, 
     * maxMessage="Longueur maximale est 100"
     * )
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $logement;

    /**
     * @Assert\Positive(message="Prix doit etre positive")
     * @ORM\Column(type="integer", nullable=true)
     */
    private $prix;

    /**
     * @ORM\ManyToOne(targetEntity=Hotel::class, inversedBy="chambre")
     */
    private $id_hotel;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getType(): ?string
    {
        return $this->type;
    }

    public function setType(?string $type): self
    {
        $this->type = $type;

        return $this;
    }

    public function getLogement(): ?string
    {
        return $this->logement;
    }

    public function setLogement(?string $logement): self
    {
        $this->logement = $logement;

        return $this;
    }

    public function getPrix(): ?int
    {
        return $this->prix;
    }

    public function setPrix(?int $prix): self
    {
        $this->prix = $prix;

        return $this;
    }

    public function getIdHotel(): ?Hotel
    {
        return $this->id_hotel;
    }

    public function setIdHotel(?Hotel $id_hotel): self
    {
        $this->id_hotel = $id_hotel;

        return $this;
    }
}
