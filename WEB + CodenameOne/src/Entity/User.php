<?php

namespace App\Entity;


use Doctrine\ORM\Mapping as ORM;
use App\Repository\UserRepository;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;
use Symfony\Component\Security\Core\User\UserInterface;
use Symfony\Component\Validator\Constraints as Assert;


/**
 * @ORM\Entity(repositoryClass=UserRepository::class)
 * @UniqueEntity(fields={"email"}, message="There is already an account with this email")
 * @UniqueEntity(fields={"cin"}, message="There is already an account with this cin")
 */
 class User  implements UserInterface
         {
             /**
              * @var int
              *
              * @ORM\Column(name="id_user", type="integer")
              * @ORM\Id
              * @ORM\GeneratedValue
              */
             private $idUser;
         
             /**
              * @var string
              *
              * @ORM\Column(name="email", unique=true, type="string", length=30, nullable=false)
              */
             private $email;
         
             /**
              * @var string The hashed password
              *
              * @ORM\Column(name="password", type="string", length=255)
              */
             private $password;
         
             /**
              * @var string
              *
              * @ORM\Column(name="nom_client", type="string", length=15)
              * @Assert\Regex(
              *        pattern     = "/^[a-z]+$/i",
              * )
              * )
              */
             private $nomClient;
         
             /**
              * @var string
              *
              * @ORM\Column(name="prenom_client", type="string", length=15)
              * @Assert\Regex(
              *        pattern     = "/^[a-z]+$/i",
              * )
              * )
              */
             private $prenomClient;
         
             /**
              * @var string
              *
              * @ORM\Column(name="CIN", type="string", length=8)
              * @Assert\Regex(pattern="/^[0-9]*$/", message="number_only") 
              */
             private $cin;
         
             /**
              * @var string
              *
              * @ORM\Column(name="numtel_client", type="string", length=8)
              * @Assert\Regex(pattern="/^[0-9]*$/", message="number_only") 
              */
             private $numtelClient;
         
             /**
              * @var string
              *
              * @ORM\Column(name="num_passport", type="string", length=10)
              * @Assert\Regex(pattern="/^[0-9]*$/", message="number_only") 
              */
             private $numPassport;
         
             /**
              * @var \DateTime
              *
              * @ORM\Column(name="datteN_client", type="date")
              */
             private $dattenClient;
         
             /**
              * @var string
              *
              * @ORM\Column(name="role", type="string", length=10)
              */
             private $role;
         
             public function setPassword(string $password): self
             {
                 $this->password = $password;
         
                 return $this;
             }
         
             public function getIdUser(): ?int
             {
                 return $this->idUser;
             }
         
             public function getEmail(): ?string
             {
                 return $this->email;
             }
         
             public function setEmail(string $email): self
             {
                 $this->email = $email;
         
                 return $this;
             }
             
            
             /**
              * @see UserInterface
              */
             public function getPassword(): ?string
             {
                 return (string) $this->password;
             } 
         
             public function getNomClient(): ?string
             {
                 return $this->nomClient;
             }
         
             public function setNomClient(string $nomClient): self
             {
                 $this->nomClient = $nomClient;
         
                 return $this;
             }
         
             public function getPrenomClient(): ?string
             {
                 return $this->prenomClient;
             }
         
             public function setPrenomClient(string $prenomClient): self
             {
                 $this->prenomClient = $prenomClient;
         
                 return $this;
             }
         
             public function getCin(): ?string
             {
                 return $this->cin;
             }
         
             public function setCin(string $cin): self
             {
                 $this->cin = $cin;
         
                 return $this;
             }
         
             public function getNumtelClient(): ?string
             {
                 return $this->numtelClient;
             }
         
             public function setNumtelClient(string $numtelClient): self
             {
                 $this->numtelClient = $numtelClient;
         
                 return $this;
             }
         
             public function getNumPassport(): ?string
             {
                 return $this->numPassport;
             }
         
             public function setNumPassport(string $numPassport): self
             {
                 $this->numPassport = $numPassport;
         
                 return $this;
             }
         
             public function getDattenClient(): ?\DateTimeInterface
             {
                 return $this->dattenClient;
             }
         
             public function setDattenClient(\DateTimeInterface $dattenClient): self
             {
                 $this->dattenClient = $dattenClient;
         
                 return $this;
             }
         
             public function getRole(): ?string
             {
                 return $this->role;
             }
         
             public function setRole(string $role): self
             {
                 $this->role = $role;
         
                 return $this;
             }
         
         
         
             protected $captchaCode;
             
             public function getCaptchaCode()
             {
               return $this->captchaCode;
             }
         
             public function setCaptchaCode($captchaCode)
             {
               $this->captchaCode = $captchaCode;
             }
         
         
             /**
              * Returning a salt is only needed, if you are not using a modern
              * hashing algorithm (e.g. bcrypt or sodium) in your security.yaml.
              *
              * @see UserInterface
              */
             public function getSalt(): ?string
             {
                 return null;
             }
             /**
              * @see UserInterface
              */
             public function eraseCredentials()
             {
                 // If you store any temporary, sensitive data on the user, clear it here
                 // $this->plainPassword = null;
             }
             /**
              * A visual identifier that represents this user.
              *
              * @see UserInterface
              */
             public function getUsername(): string
             {
                 return (string) $this->email;
             }
             
              /**
              * @ORM\Column(type="json",nullable=true)
              */
             private $roles = [];
      
             /**
              * @ORM\Column(type="string", length=255, nullable=true)
              */
             private $activation_token;

             /**
              * @see UserInterface
              */
             public function getRoles(): array
             {
                 $roles = $this->roles;
                 $roles[] = 'ROLE_USER';
         
                 return array_unique($roles);
             }
         
             public function setRoles(array $roles): self
             {
                 $this->roles = $roles;
         
                 return $this;
             }
   
             public function getActivationToken(): ?string
             {
                 return $this->activation_token;
             }

             public function setActivationToken(string $activation_token): self
             {
                 $this->activation_token = $activation_token;

                 return $this;
             }
             
         }
