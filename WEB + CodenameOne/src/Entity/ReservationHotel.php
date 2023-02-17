<?php

namespace App\Entity;

use App\Repository\ReservationHotelRepository;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\ORM\Mapping as ORM;

use Symfony\Component\Validator\Constraints\GreaterThan;
use Symfony\Component\Validator\Constraints\NotBlank;
use Symfony\Component\Validator\Constraints\Date;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Serializer\Annotation\Groups;

/**
 * @ORM\Entity(repositoryClass=ReservationHotelRepository::class)
 */
class ReservationHotel
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @Assert\NotBlank(message="Id User doit etre non vide")
     * )
     * @ORM\Column(type="integer")
     */
    private $id_user;

    /**
     * @Assert\Date()
     * @Assert\GreaterThan("today")
     * @ORM\Column(type="date")
     */
    private $date_debut;

    /**
     * @Assert\Date()
     *  @Assert\Expression(
   *     "this.getDateDebut() < this.getDateFin()",
   *     message="La date fin ne doit pas être antérieure à la date début")
     * @ORM\Column(type="date")
     */
    private $date_fin;

    /**
     * @ORM\Column(type="integer")
     */
    private $id_hotel;

    /**
     * @Assert\NotBlank(message="Nombre personne doit etre non vide")
     * @Assert\Length(
     * max = 3, 
     * maxMessage="Longueur maximale est 3"
     * )
     * @ORM\Column(type="integer")
     */
    private $nbrperso;

    /**
     * @Assert\NotBlank(message="Id chambre doit etre non vide")
     * )
     * @ORM\Column(type="integer")
     */
    private $id_chambre;

    /**
     * @Assert\Positive(message="Prix doit etre positive")
     * @Assert\NotBlank(message="PrixTotal doit etre non vide")
     * )
     * @ORM\Column(type="integer")
     */
    private $prixtotal;

    /**
     * @ORM\ManyToOne(targetEntity=Hotel::class, inversedBy="reservationHotels")
     * @ORM\JoinColumn(nullable=false)
     * @ORM\Column(type="integer")
     * @Groups ("post:read")
     */
    private $idhotel;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getIdUser(): ?int
    {
        return $this->id_user;
    }

    public function setIdUser(int $id_user): self
    {
        $this->id_user = $id_user;

        return $this;
    }

    public function getDateDebut(): ?\DateTimeInterface
    {
        return $this->date_debut;
    }

    public function setDateDebut(\DateTimeInterface $date_debut): self
    {
        $this->date_debut = $date_debut;

        return $this;
    }

    public function getDateFin(): ?\DateTimeInterface
    {
        return $this->date_fin;
    }

    public function setDateFin(\DateTimeInterface $date_fin): self
    {
        $this->date_fin = $date_fin;

        return $this;
    }

    public function getIdHotel(): ?int
    {
        return $this->idhotel;
    }

    public function setIdHotel(int $idhotel): self
    {
        $this->idhotel = $idhotel;

        return $this;
    }

    public function getNbrperso(): ?int
    {
        return $this->nbrperso;
    }

    public function setNbrperso(int $nbrperso): self
    {
        $this->nbrperso = $nbrperso;

        return $this;
    }
  public function getId_Hotel(): ?int
    {
        return $this->id_hotel;
    }

    public function setId_Hotel(int $id_hotel): self
    {
        $this->id_hotel = $id_hotel;

        return $this;
    }
    public function getIdChambre(): ?int
    {
        return $this->id_chambre;
    }

    public function setIdChambre(int $id_chambre): self
    {
        $this->id_chambre = $id_chambre;

        return $this;
    }

    public function getPrixtotal(): ?int
    {
        return $this->prixtotal;
    }

    public function setPrixtotal(int $prixtotal): self
    {
        $this->prixtotal = $prixtotal;

        return $this;
    }
}
