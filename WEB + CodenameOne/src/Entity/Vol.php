<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Serializer\Annotation\Groups;



/**
 * Vol
 *
 * @ORM\Table(name="vol")
 * @ORM\Entity(repositoryClass="App\Repository\VolRepository")
 */
class Vol
{
    /**
     * @var int

     * @ORM\Column(name="id_vol", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     * @Groups ("post:read")

     */
    private $idVol;

    /**
     * @var string
     * @Assert\NotBlank(message="Ref doit etre non vide")
     * @Assert\Length(
     * max = 5,
     * maxMessage="Longueur maximale est 5",
     * min = 4,
     * minMessage="Longueur minimale est 4"
     * )
     * @ORM\Column(name="ref", type="string", length=20, nullable=false)
     * @Groups ("post:read")
     */
    private $ref;

    /**
     * @var string
     * @Assert\NotBlank(message="DEPART doit etre non vide")
     * @Assert\Length(
     * max = 40, 
     * maxMessage="Longueur maximale est 40"
     
     * )
     * @ORM\Column(name="depart", type="string", length=40, nullable=false)
     *  @Groups ("post:read")
     */
    private $depart;

    /**
     * @var string
     * @Assert\NotBlank(message="DESTINATION doit etre non vide")
     * @Assert\Length(
     * max = 40, 
     * maxMessage="Longueur maximale est 40"
     * )
     * @ORM\Column(name="destination", type="string", length=40, nullable=false)
     *  @Groups ("post:read")
     */
    private $destination;

    /**
     * @var \DateTime
     * @Assert\NotBlank(message="DATE DEPART doit etre non vide")
     * @Assert\GreaterThan("today")
     * @ORM\Column(name="date_d", type="date", nullable=false)
     *  @Groups ("post:read")
     */
    private $dateD;

    /**
     * @var \DateTime
     * @Assert\GreaterThan("today")
     * @Assert\NotBlank(message="DATE RETOUR doit etre non vide")
     * @ORM\Column(name="date_r", type="date", nullable=false)
     *  @Groups ("post:read")
     */
    private $dateR;

    /**
     * @var float
     * @Assert\NotBlank(message="PRIX doit etre non vide")
     * @ORM\Column(name="prix_v", type="float", precision=10, scale=0, nullable=false)
     *  @Groups ("post:read")
     */
    private $prixV;

    public function getIdVol(): ?int
    {
        return $this->idVol;
    }
    public function setIdVol(string $idVol): self
    {
        $this->idVol = $idVol;

        return $this;
    }
    public function getRef(): ?string
    {
        return $this->ref;
    }

    public function setRef(string $ref): self
    {
        $this->ref = $ref;

        return $this;
    }

    public function getDepart(): ?string
    {
        return $this->depart;
    }

    public function setDepart(string $depart): self
    {
        $this->depart = $depart;

        return $this;
    }

    public function getDestination(): ?string
    {
        return $this->destination;
    }

    public function setDestination(string $destination): self
    {
        $this->destination = $destination;

        return $this;
    }

    public function getDateD(): ?\DateTimeInterface
    {
        return $this->dateD;
    }

    public function setDateD(\DateTimeInterface $dateD): self
    {
        $this->dateD = $dateD;

        return $this;
    }

    public function getDateR(): ?\DateTimeInterface
    {
        return $this->dateR;
    }

    public function setDateR(\DateTimeInterface $dateR): self
    {
        $this->dateR = $dateR;

        return $this;
    }

    public function getPrixV(): ?float
    {
        return $this->prixV;
    }

    public function setPrixV(float $prixV): self
    {
        $this->prixV = $prixV;

        return $this;
    }


}
