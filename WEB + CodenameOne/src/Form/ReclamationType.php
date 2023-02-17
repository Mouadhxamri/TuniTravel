<?php

namespace App\Form;

use App\Entity\Reclamation;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class ReclamationType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('titre',ChoiceType::class,array('choices'=> array(
                'Reclamation vol'=> 'reclamationVol',
                'Reclamation hotel'=> 'reclamationHotel',
                'Reclamation voiture'=> 'reclamationVoiture',
            )))
            ->add('contenu',TextareaType::class ,['attr'=>[
                'placeholder'=>'veuillez detailler votre Reclamation pour nous aider a mieux comprendre votre probleme'] ])

        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Reclamation::class,
        ]);
    }
}
