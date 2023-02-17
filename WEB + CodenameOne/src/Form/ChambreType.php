<?php

namespace App\Form;

use App\Entity\Chambre;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;

class ChambreType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('type', ChoiceType::class, [
                'choices'  => [
                    'Chambre Single' => "Single",
                    'Chambre Double' => "Double",
                ],
            ])
            ->add('logement', ChoiceType::class, [
                'choices'  => [
                    'Demi Pension' => "Demi Pension",
                    'Pension Complete' => "Pension Complete",
                    'All Inclusive' => "All Inclusive",
                ],
            ])
            ->add('prix')
            ->add('id_hotel')
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Chambre::class,
        ]);
    }
}
