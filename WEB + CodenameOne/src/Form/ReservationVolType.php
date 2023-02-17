<?php

namespace App\Form;

use App\Entity\ReservationVol;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;

class ReservationVolType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('idUser')
            ->add('dateDebut')
            ->add('dateFin')
            ->add('prixTotal')
            ->add('idVol')
            ->add('nbrpsg')
            ->add('aerocomp')
            ->add('classe')
            ->add('Submit',SubmitType::class)
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => ReservationVol::class,
        ]);
    }
}
