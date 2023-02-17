<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * ReservationVol
 *
 * @ORM\Table(name="reservation_vol")
 * @ORM\Entity
 */
class ReservationVol
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_resV", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idResv;

    /**
     * @var int
     *
     * @ORM\Column(name="id_user", type="integer", nullable=false)
     */
    private $idUser;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_debut", type="date", nullable=false)
     */
    private $dateDebut;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_fin", type="date", nullable=false)
     */
    private $dateFin;

    /**
     * @var float
     *
     * @ORM\Column(name="prix_total", type="float", precision=10, scale=0, nullable=false)
     */
    private $prixTotal;

    /**
     * @var int
     *
     * @ORM\Column(name="id_vol", type="integer", nullable=false)
     */
    private $idVol;

    /**
     * @var int
     *
     * @ORM\Column(name="nbrpsg", type="integer", nullable=false)
     */
    private $nbrpsg;

    /**
     * @var string
     *
     * @ORM\Column(name="aerocomp", type="string", length=20, nullable=false)
     */
    private $aerocomp;

    /**
     * @var string
     *
     * @ORM\Column(name="classe", type="string", length=20, nullable=false)
     */
    private $classe;

    public function getIdResv(): ?int
    {
        return $this->idResv;
    }

    public function getIdUser(): ?int
    {
        return $this->idUser;
    }

    public function setIdUser(int $idUser): self
    {
        $this->idUser = $idUser;

        return $this;
    }

    public function getDateDebut(): ?\DateTimeInterface
    {
        return $this->dateDebut;
    }

    public function setDateDebut(\DateTimeInterface $dateDebut): self
    {
        $this->dateDebut = $dateDebut;

        return $this;
    }

    public function getDateFin(): ?\DateTimeInterface
    {
        return $this->dateFin;
    }

    public function setDateFin(\DateTimeInterface $dateFin): self
    {
        $this->dateFin = $dateFin;

        return $this;
    }

    public function getPrixTotal(): ?float
    {
        return $this->prixTotal;
    }

    public function setPrixTotal(float $prixTotal): self
    {
        $this->prixTotal = $prixTotal;

        return $this;
    }

    public function getIdVol(): ?int
    {
        return $this->idVol;
    }

    public function setIdVol(int $idVol): self
    {
        $this->idVol = $idVol;

        return $this;
    }

    public function getNbrpsg(): ?int
    {
        return $this->nbrpsg;
    }

    public function setNbrpsg(int $nbrpsg): self
    {
        $this->nbrpsg = $nbrpsg;

        return $this;
    }

    public function getAerocomp(): ?string
    {
        return $this->aerocomp;
    }

    public function setAerocomp(string $aerocomp): self
    {
        $this->aerocomp = $aerocomp;

        return $this;
    }

    public function getClasse(): ?string
    {
        return $this->classe;
    }

    public function setClasse(string $classe): self
    {
        $this->classe = $classe;

        return $this;
    }


}
