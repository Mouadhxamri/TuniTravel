<?php

namespace App\Entity;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use App\Repository\HotelRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Serializer\Annotation\Groups;
/**
 * @ORM\Entity(repositoryClass=HotelRepository::class)
 */
class Hotel
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     * @Groups ("post:read")
     */
    private $id;

    /**
     * @Assert\NotBlank(message="Ref doit etre non vide")
     * @Assert\Length(
     * max = 4, 
     * maxMessage="Longueur maximale est 4"
     * )
     * @ORM\Column(type="integer")
     * @Groups ("post:read")
     */
    private $ref;

    /**
     * @Assert\NotBlank(message="Nom doit etre non vide")
     * @Assert\Length(
     * max = 20, 
     * maxMessage="Longueur maximale est 20"
     * )
     * @ORM\Column(type="string", length=255)
     * @Groups ("post:read")
     */
    private $nom;

    /**
     * @Assert\NotBlank(message="Description doit etre non vide")
     * @Assert\Length(
     * max = 255, 
     * maxMessage="Longueur maximale est 255"
     * )
     * @ORM\Column(type="string", length=255)
     * @Groups ("post:read")
     */
    private $description;

    /**
     * @Assert\NotBlank(message="addresse doit etre non vide")
     * @Assert\Length(
     * max = 20, 
     * maxMessage="Longueur maximale est 20"
     * )
     * @ORM\Column(type="string", length=255)
     * @Groups ("post:read")
     */
    private $adresse;

    /**
     * @Assert\NotBlank(message="Prix doit etre non vide")
     * @Assert\Positive(message="Prix doit etre positive") 
     * @ORM\Column(type="float")
     * @Groups ("post:read")
     */
    private $prix;

     /**
     * @ORM\Column(type="string", length=500,nullable=true)
     * @Groups ("post:read")
     */
    private $image;
     
    /**
     * @ORM\OneToMany(targetEntity=Chambre::class, mappedBy="id_hotel")
     * @Groups ("post:read")
     */
    private $chambre;

    /**
     * @Assert\File(maxSize="6000000")
     * @Groups ("post:read")
     */
    private $file;

    /**
     * @ORM\OneToMany(targetEntity=ReservationHotel::class, mappedBy="idhotel")
     */
    private $reservationHotels;
    
    public function __construct()
    {
        $this->chambre = new ArrayCollection();
        $this->reservationHotels = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getRef(): ?int
    {
        return $this->ref;
    }

    public function setRef(int $ref): self
    {
        $this->ref = $ref;

        return $this;
    }
    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): self
    {
        $this->nom = $nom;

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(string $description): self
    {
        $this->description = $description;

        return $this;
    }

    public function getAdresse(): ?string
    {
        return $this->adresse;
    }

    public function setAdresse(string $adresse): self
    {
        $this->adresse = $adresse;

        return $this;
    }

    public function getPrix(): ?float
    {
        return $this->prix;
    }

    public function setPrix(float $prix): self
    {
        $this->prix = $prix;

        return $this;
    }

 /**
     * @return mixed
     */
    public function getImage()
    {
        return $this->image;
    }

    /**
     * @param mixed $image
     */
    public function setImage($image): void
    {
        $this->image = $image;
    }

    //UPLOAD IMAGE
    //GET PUBLIC FOLDER
  /*  public function getPublicFolder() {
        $webPath = $this->get('kernel')->getProjectDir() . '/public/uploads/produit_image';

        return $webPath;


    }*/
     /**
     * @return UploadedFile
     */
    public function getFile()
    {
        return $this->file;
    }

    /**
     * @param UploadedFile
     */
    public function setFile($file): void
    {
        $this->file = $file;
    }


    
    public function upload()
    {
        if(null === $this->getFile()) {
            return;
        }

        $this->getFile()->move(
            $this->getPublicFolder(),//destinataire
            $this->getFile()->getClientOriginalName()//esm fichier (image)
        );

        $this->image = $this->getFile()->getClientOriginalName();//

        $this->file = null; // liberation memoire
    }
    
/**
     * @return Collection<int, Chambre>
     */
    public function getChambre(): Collection
    {
        return $this->chambre;
    }

    public function addChambre(Chambre $chambre): self
    {
        if (!$this->chambre->contains($chambre)) {
            $this->chambre[] = $chambre;
            $chambre->setIdHotel($this);
        }

        return $this;
    }

    public function removeChambre(Chambre $chambre): self
    {
        if ($this->chambre->removeElement($chambre)) {
            // set the owning side to null (unless already changed)
            if ($chambre->getIdHotel() === $this) {
                $chambre->setIdHotel(null);
            }
        }

        return $this;
    }
    
    public function __toString()
    {
        return $this->nom;
    }

    /**
     * @return Collection<int, ReservationHotel>
     */
    public function getReservationHotels(): Collection
    {
        return $this->reservationHotels;
    }

    public function addReservationHotel(ReservationHotel $reservationHotel): self
    {
        if (!$this->reservationHotels->contains($reservationHotel)) {
            $this->reservationHotels[] = $reservationHotel;
            $reservationHotel->setIdhotel($this);
        }

        return $this;
    }

    public function removeReservationHotel(ReservationHotel $reservationHotel): self
    {
        if ($this->reservationHotels->removeElement($reservationHotel)) {
            // set the owning side to null (unless already changed)
            if ($reservationHotel->getIdhotel() === $this) {
                $reservationHotel->setIdhotel(null);
            }
        }

        return $this;
    }




}
